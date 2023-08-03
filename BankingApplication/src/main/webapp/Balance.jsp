<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance Check</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
    <section class="balance">
	<h3>
	<%
		session = request.getSession();
		out.println("Balance = "+session.getAttribute("Balance"));
	%>
	</h3><br>
	<a href="Balancefail.jsp">Balance Failed</a>
	</section>
</body>
</html>