<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<section class="container-jsp">
	<h1 align="center"> Welcome to the Bank </h1><br><br>
	<%
		session = request.getSession();
		String s1 = (String)session.getAttribute("Cust_Name");
		out.println(s1+" Welcome to your account. \n Please select an operation to perform.");
	%>
	<br><br>
	<a href="CheckBalance">- Check Balance</a><br><br>
	<a href="ChangePassword.html">- Change Password</a><br><br>
	<a href="Loan.jsp">- Apply Loan</a><br><br>
	<a href="Transfer.html">- Transfer Amount</a><br><br>
	<a href="Logout">- Logout</a>
</section>
</body>
</html>