package Banking;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res1;
	private ResultSet res2;
	private ResultSet res3;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession hs = req.getSession();

		int Cust_Id = Integer.parseInt(req.getParameter("Cust_Id"));
		String IFSC_Code = req.getParameter("IFSC_Code");
		// int Acc_No = Integer.parseInt(req.getParameter("Acc_No"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		int Amount_Transfered = Integer.parseInt(req.getParameter("Amount_Transfer"));
		String Bank_Name = (String) hs.getAttribute("Bank_Name");
		int Sender_Acc_No = (int) hs.getAttribute("Acc_No");
		String Receiver_IFSC_Code = (req.getParameter("Receiver_IFSC_Code"));
		int Reciever_Acc_No = Integer.parseInt(req.getParameter("Receiver_Acc_No"));
		// int Trans_Id = (int)hs.getAttribute("Trans_Id");

		// random
		Random r = new Random();
		String Trans_Id = (10000 + r.nextInt(900000)) + "";

		String url = "jdbc:mysql://localhost:3306/Banking";
		String user = "root";
		String pwd = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);

			pstmt = con
					.prepareStatement("Select * from Bank_App where Cust_Id=? and IFSC_Code=? and Acc_No=? and pin=?");

			pstmt.setInt(1, Cust_Id);
			pstmt.setString(2, IFSC_Code);
			pstmt.setInt(3, Sender_Acc_No);
			pstmt.setInt(4, pin);

			res1 = pstmt.executeQuery();

			if (res1.next() == true) {
				pstmt = con.prepareStatement("select * from Bank_App where IFSC_Code=? and Acc_No=?");
				pstmt.setString(1, Receiver_IFSC_Code);
				pstmt.setInt(2, Reciever_Acc_No);

				res2 = pstmt.executeQuery();

			} else {
				res.sendRedirect("/BankingApplication/TransferFail.jsp");
			}
			if (res2.next() == true) {
				pstmt = con.prepareStatement("select Balance from Bank_App where Acc_No=?");
				pstmt.setInt(1, Sender_Acc_No);
				res3 = pstmt.executeQuery();
				res3.next();
				int Balance = res3.getInt(1);
				if (Balance > Amount_Transfered) {
					pstmt = con.prepareStatement("update Bank_App set Balance=Balance-? where Acc_No=?");
					pstmt.setInt(1, Amount_Transfered);
					pstmt.setInt(2, Sender_Acc_No);

					int x1 = pstmt.executeUpdate();
					if (x1 > 0) {
						pstmt = con.prepareStatement("update Bank_App set Balance=Balance+? where Acc_No=?");
						pstmt.setInt(1, Amount_Transfered);
						pstmt.setInt(2, Reciever_Acc_No);

						int x2 = pstmt.executeUpdate();
						if (x2 > 0) {
							pstmt = con.prepareStatement("Insert into transfer_status values(?,?,?,?,?,?,?,?)");
							pstmt.setInt(1, Cust_Id);
							pstmt.setString(2, Bank_Name);
							pstmt.setString(3, IFSC_Code);
							pstmt.setInt(4, Sender_Acc_No);
							pstmt.setString(5, Receiver_IFSC_Code);
							pstmt.setInt(6, Reciever_Acc_No);
							pstmt.setInt(7, Amount_Transfered);
							pstmt.setString(8, Trans_Id);

							int x3 = pstmt.executeUpdate();
							if (x3 > 0) {
								res.sendRedirect("/BankingApplication/TransferSuccess.html");
								return;
							}

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("/BankingApplication/TransferFail.jsp");
	}

}
