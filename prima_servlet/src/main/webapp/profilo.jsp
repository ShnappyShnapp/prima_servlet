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
    <link rel="stylesheet" href="stylesheets/mainStyle.css">
    <style>
	@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Permanent+Marker&display=swap');
	</style>
</head>
<body>
	<div id="container">
		<%@ include file="/WEB-INF/header.jsp" %>
		<nav>
			<a href ="impostazioni.jsp">Impostazioni</a>
			<a href="index.jsp">Home</a>
		</nav>
		<div class="bigcard">
			<article>
				<h1>Benvenuto, ${sessionScope.utenteLoggato.username}!</h1>
	    		<h2>${sessionScope.utenteLoggato.nome} ${sessionScope.utenteLoggato.cognome}, ${sessionScope.utenteLoggato.eta}.</h2>
	    		<h4>(${sessionScope.utenteLoggato.email})</h4>
	    	</article>
		</div>
	</div>
</body>
</html>
