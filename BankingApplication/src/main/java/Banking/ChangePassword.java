package Banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";
		
		HttpSession hs = req.getSession();
		int OldPassword = Integer.parseInt(req.getParameter("OldPassword"));
		int Acc_No = (int)hs.getAttribute("Acc_No");
		
		int NewPassword = Integer.parseInt(req.getParameter("NewPassword"));
		int ConfirmPassword = Integer.parseInt(req.getParameter("ConfirmPassword"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			if(NewPassword==ConfirmPassword) {
				pstmt = con.prepareStatement("update Bank_App set pin=? where Acc_No=? and pin=?");
				
				pstmt.setInt(1,  NewPassword);
				pstmt.setInt(2,  Acc_No);
				pstmt.setInt(3,  OldPassword);
				int x = pstmt.executeUpdate();
				if(x>0) {
					res.sendRedirect("/BankingApplication/PasswordSuccess.html");
				}
				
			else {
				res.sendRedirect("/BankingApplication/PasswordFail.html");
			}
		}
			else {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
