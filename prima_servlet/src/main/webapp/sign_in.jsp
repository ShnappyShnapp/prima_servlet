<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign in</title>
		<link rel="stylesheet" href="stylesheets/mainStyle.css">
    	<style>
		@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Permanent+Marker&display=swap');
		</style>
	</head>
	<body>
		<div id="container">
			<%@ include file="/WEB-INF/header.jsp" %>
			<div class="smallcard">
				<h1>Sign in</h1>
				<!-- quì è dove gli dico come mostrare i messaggi di errore -->
				<% if (request.getAttribute("errore") != null) { %>
					<p class="errore">${errore}</p>
				<% } %>
				
				<form id="sign_in" action="${pageContext.request.contextPath}/getUserServlet" method="post">
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
					<input type="submit" value="Entra" class="button">
				</form>
			</div>
		</div>
	</body>
</html>