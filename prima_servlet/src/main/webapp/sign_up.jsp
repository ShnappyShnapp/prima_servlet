<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Questa sarà la pagina di iscrizione dell'utente -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign Up</title>
	</head>
	<body>
		<div>
			<h1>Sign up</h1>
			<!-- l'effettiva finestra dove prendiamo i dati che poi salveremo nel db -->
			<form id="sign_up">
				<label for="nome">Il tuo nome:</label>
				<input type="text" id="nome" name="nome"><br>
				<label for="cognome">Il tuo cognome:</label>
				<input type="text" id="cognome" name="cognome"><br>
				<label for="eta">La tua età:</label>
				<input type="number" id="eta" name="eta"><br>
				<br>
				<label for="username">Il tuo username:</label>
				<input type="text" id="username" name="username"><br>
				<label for="email">La tua email:</label>
				<input type="email" id="email" name="email"><br>
				<label for="pssw">La tua password:</label>
				<input type="password" id="pssw" name="pssw"><br>
				<br>
				<input type="submit">
			</form>
		</div>
	</body>
</html>