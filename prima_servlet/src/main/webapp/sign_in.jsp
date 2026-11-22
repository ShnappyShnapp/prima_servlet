<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign in</title>
	</head>
	<body>
		<div>
			<h1>Sign in</h1>
			<!-- quì è dove gli dico come mostrare i messaggi di errore -->
			<% if (request.getAttribute("errore") != null) { %>
				<p style="color:red;">${errore}</p>
			<% } %>
			
			<form id="sign_in" action="${pageContext.request.contextPath}/getUserServlet" method="post">
				<label for="username">Il tuo username:</label>
				<input type="text" id="username" name="username" value="${param.username}" required><br>
				<label for="email">La tua email:</label>
				<input type="email" id="email" name="email" value="${param.email}" required><br>
				<label for="pssw">La tua password:</label>
				<input type="password" id="pssw" name="pssw" required><br>
				<br>
				<input type="submit" value="Entra">
			</form>
		</div>
	</body>
</html>