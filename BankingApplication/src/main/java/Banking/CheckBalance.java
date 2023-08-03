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

@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultset;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession hs = req.getSession();
		int Acc_No = (int)hs.getAttribute("Acc_No");
		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			pstmt = con.prepareStatement("select Balance from bank_app where Acc_No=?");
			pstmt.setInt(1, Acc_No);
			
			
			ResultSet resultset = pstmt.executeQuery();
			if(resultset.next()==true) {
				hs.setAttribute("Balance", resultset.getInt("Balance"));
//				hs.setAttribute("Cust_Name", resultset.getString("Cust_Name"));
				res.sendRedirect("/BankingApplication/Balance.jsp");
			}
			else {
				res.sendRedirect("/BankingApplication/Balancefail.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
