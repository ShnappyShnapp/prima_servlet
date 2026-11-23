<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Questa sarà la pagina di iscrizione dell'utente -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up</title>
		<link rel="stylesheet" href="stylesheets/mainStyle.css">
    	<style>
		@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Permanent+Marker&display=swap');
		</style>
	</head>
	<body>
		<div id="container">
			<%@ include file="/WEB-INF/header.jsp" %>
			<div class="smallcard">
				<h1>Sign up</h1>
	
				<!-- quì è dove gli dico come mostrare i messaggi di errore -->
				<% if (request.getAttribute("errore") != null) { %>
					<p style="color:red;">${errore}</p>
				<% } %>
	
				<!-- l'effettiva finestra dove prendiamo i dati che poi salveremo nel db -->
				<form id="sign_up" action="${pageContext.request.contextPath}/PrimaServlet" method="post">
					<section class="row">
						<label for="nome">Il tuo nome:</label>
						<input type="text" id="nome" name="nome" value="${param.nome}" required>
					</section>
					<section class="row">
						<label for="cognome">Il tuo cognome:</label>
						<input type="text" id="cognome" name="cognome" value="${param.cognome}" required>
					</section>
					<section class="row">
						<label for="eta">La tua età:</label>
						<input type="number" id="eta" name="eta" min="0" value="${param.eta}" required>
					</section>
					<hr class="dotted">
					<section class="row">
						<label for="username">Il tuo username:</label>
						<input type="text" id="username" name="username" value="${param.username}" required>
					</section>
					<section class="row">
						<label for="email">La tua email:</label>
						<input type="email" id="email" name="email" value="${param.email}" required>
					</section>
					<section class="row">
						<label for="pssw">La tua password:</label>
						<input type="password" id="pssw" name="pssw" required>
					</section>
					<hr class="dotted">
					<input type="submit" value="Registrati" class="button">
				</form>
			</div>
		</div>
	</body>
</html>
