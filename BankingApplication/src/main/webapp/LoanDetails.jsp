<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
 	<h1 align="center">Loan Details</h1>
 	<h1 align="center">
 	<%
 	session = request.getSession();
 	out.println("Hii "+session.getAttribute("Cust_Name"));
 	%>
 	<br>
 	Here are the Details:
 	</h1>
 	
 	<h2 align="center">
 	<%
	session = request.getSession();
 	int x = (int)session.getAttribute("Loan_Id");
 	out.println("Loan_Id: " +x);
 	%>
 	<br>
 	<%
 	session = request.getSession();
 	String s1 = (String)session.getAttribute("Loan_Type");
	out.println("Loan_Type: "+s1);
 	%>
 	<br>
 	<%
 	session = request.getSession();
	int y = (int)session.getAttribute("Tenure");
	out.println("Tenure: "+y);
 	%>
	<br>
	<%
	session = request.getSession();
	float z = (float)session.getAttribute("Intrest");
	out.println("Intrest: "+z);
	%>
	<br>
	<%
	session = request.getSession();
	String s2 = (String)session.getAttribute("Description");
	out.println("Description: "+s2);
	%>
	</h2>
 	
 	<h2 align="center"><a href="Home.jsp">Click here to redirect</a></h2>
 	
 	<h1 align="center">Thank you for applying!!! Our lender will talk to you within couple of days</h1>
</body>
</html>