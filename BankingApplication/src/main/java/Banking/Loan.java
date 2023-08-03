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

@WebServlet("/Loan")
public class Loan extends HttpServlet{
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet resultset;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession hs = req.getSession();
		String Loan_Type = req.getParameter("Loan_Type");
		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			pstmt = con.prepareStatement("select * from loan where Loan_Type=?");
			pstmt.setString(1, Loan_Type);
			
			
			ResultSet resultset = pstmt.executeQuery();
			if(resultset.next()==true) {
				hs.setAttribute("Loan_Id",resultset.getInt("Loan_Id"));
				hs.setAttribute("Loan_Type",resultset.getString("Loan_Type"));
				hs.setAttribute("Tenure",resultset.getInt("Tenure"));
				hs.setAttribute("Intrest",resultset.getFloat("Intrest"));
				hs.setAttribute("Description",resultset.getString("Description"));
				res.sendRedirect("/BankingApplication/LoanDetails.jsp");
			}
			else {
				res.sendRedirect("/BankingApplication/LoanFail.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
