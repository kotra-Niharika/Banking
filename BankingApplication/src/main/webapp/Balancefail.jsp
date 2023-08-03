<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance Fail</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<section class="balance-fail">
	<h3>
	<%
	session = request.getSession();
	out.println("Dear "+session.getAttribute("Cust_Name")+" your balance could not be fetched");
	%>
	</h3><br>
	<a href="Home.jsp">Redirect</a>
</section>
</body>
</html>