<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<% if (session.getAttribute("utenteLoggato") == null) {
       response.sendRedirect("sign_in.jsp");
       return;
   } %>
   
   
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profilo</title>
    <link rel="stylesheet" href="styles/mainStyle.css">
</head>
<body>
	<header>
		<h1>Rent a Grandma</h1>
	</header>
	<nav>
		<a href="index.jsp">Home</a>
	</nav>
	<div>
		<h1>Benvenuto, ${sessionScope.utenteLoggato.username}!</h1>
    	<p>${sessionScope.utenteLoggato.nome} ${sessionScope.utenteLoggato.cognome}, ${sessionScope.utenteLoggato.eta}.</p>
    	<h4>(${sessionScope.utenteLoggato.email})</h4>
	</div>
</body>
</html>
