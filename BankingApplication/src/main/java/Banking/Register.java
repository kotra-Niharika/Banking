package Banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int Bank_Id = Integer.parseInt(req.getParameter("Bank_Id"));
		String Bank_Name = req.getParameter("Bank_Name");
		String IFSC_Code = req.getParameter("IFSC_Code");
		int Acc_No = Integer.parseInt(req.getParameter("Acc_No"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int Cust_Id = Integer.parseInt(req.getParameter("Cust_Id"));
		String Cust_Name = req.getParameter("Cust_Name");
		int Balance = Integer.parseInt(req.getParameter("Balance"));
		String Email = req.getParameter("Email");
		Long Phone = Long.parseLong(req.getParameter("Phone"));
		
		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			
			pstmt = con.prepareStatement("insert into bank_app values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, Bank_Id);
			pstmt.setString(2, Bank_Name);
			pstmt.setString(3, IFSC_Code);
			pstmt.setInt(4, Acc_No);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, Cust_Id);
			pstmt.setString(7, Cust_Name);
			pstmt.setInt(8, Balance);
			pstmt.setString(9, Email);
			pstmt.setLong(10, Phone);
			
			int x = pstmt.executeUpdate();
			if(x>0) {
				res.sendRedirect("/BankingApplication/Success.html");
			}
			else {
				res.sendRedirect("/BankingApplication/Fail.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
