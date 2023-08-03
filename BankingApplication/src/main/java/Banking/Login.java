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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int Cust_Id = Integer.parseInt(req.getParameter("Cust_Id"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		
		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";
		HttpSession hs = req.getSession(true);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			pstmt = con.prepareStatement("select * from bank_app where Cust_Id=? and pin=?");
			pstmt.setInt(1, Cust_Id);
			pstmt.setInt(2, pin);
			
			ResultSet resultset = pstmt.executeQuery();
			if(resultset.next()==true) {
				hs.setAttribute("Acc_No", resultset.getInt("Acc_No"));
				hs.setAttribute("pin", resultset.getInt("pin"));
				hs.setAttribute("Cust_Name", resultset.getString("Cust_Name"));
				res.sendRedirect("/BankingApplication/Home.jsp");
			}
			else {
				res.sendRedirect("/BankingApplication/loginfail.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
