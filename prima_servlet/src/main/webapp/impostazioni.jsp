<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% if (session.getAttribute("utenteLoggato") == null) {
       response.sendRedirect("sign_in.jsp");
       return;
   } %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Impostazioni</title>
		<link rel="stylesheet" href="stylesheets/mainStyle.css">
   		<style>
			@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Permanent+Marker&display=swap');
		</style>
	</head>
	<body>
		<div id="container">
			<%@ include file="/WEB-INF/header.jsp" %>
			<nav>
				<a href="profilo.jsp">Profilo</a>
				<a href="index.jsp">Home</a>
			</nav>
			<div class="bigcard">
				<h1>Impostazioni</h1>
				<article>
					<h2>Modifica Profilo</h2>
					<% if (request.getAttribute("errore") != null) { %>
    					<p class="errore">${errore}</p>
					<% } %>
					<form id="modify_user" action="${pageContext.request.contextPath}/modifyUserServlet" method="post">
						<section class="row">
							<label for="nome">Nome:</label>
							<input type="text" id="nome" name="nome" 
								value="${not empty param.nome ? param.nome : sessionScope.utenteLoggato.nome}">
						</section>
						<section class="row">
							<label for="cognome">Cognome:</label>
							<input type="text" id="cognome" name="cognome" 
								       value="${not empty param.cognome ? param.cognome : sessionScope.utenteLoggato.cognome}">
						</section>
						<section class="row">
							<label for="eta">Eta:</label>
							<input type="number" id="eta" min="1" max="129" name="eta" 
								value="${not empty param.eta ? param.eta : sessionScope.utenteLoggato.eta}">
						</section>
						<section class="row">
							<label for="username">Username:</label>
							<input type="text" id="username" name="username" 
								value="${not empty param.username ? param.username : sessionScope.utenteLoggato.username}">
						</section>
						<hr class="dotted">
						<input type="submit" value="Modifica" class="button">
					</form>
				</article>
			</div>
		</div>
	</body>
</html>