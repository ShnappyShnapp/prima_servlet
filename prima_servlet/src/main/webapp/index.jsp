<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<!-- Questa è la landing page, cioè la prima pagina che l'utente vede una volta entrato nel nostro sito. -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" href="styling/mainStyle.css">
		<style>
		@import url('https://fonts.googleapis.com/css2?family=Patrick+Hand&family=Permanent+Marker&display=swap');
		</style>
	</head>

	<body>
		<div id="container">
<!-- La sezione titolo della pagina -->
			<header>
				<h1 id="title">Rent a Grandma</h1>
				<h2>Noleggia una nonna a ore!</h2>
				<h5>Facile e veloce.</h5>
			</header>
	<!-- La sezione navigazione -->
			<nav>
				<a></a><!-- per future navigazioni -->
				<a href="sign_in.jsp">Profilo</a>
			</nav>
			<!-- sezione contenuto pagina -->
			<div class ="bigcard">
				<article>
					<h2>Servizi</h2>
					<p>&#x2714 Porzioni da quattro persone servita a una sola;<br>
					&#x2714 Commenti non richiesti sulla tua vita sentimentale;<br>
					&#x2714 <i>"Copriti che prendi freddo"</i> anche ad Agosto;<br>
					&#x2714 Recupero del Tupperware garantito, ovunque tu sia.
					</p>
				</article>
				<article>
					<h2>Piani Tariffari</h2>
					<p><b>Base</b><br>
					Un piatto di pasta e una domanda su quando ti sposi.</p>
					<p><b>Premium</b><br>
					Tre portate più dolce, inclusa la frase<br>
					<i>"Ma non mangi niente!"</i></p>
					<p><b>Deluxe</b><br>
					La nonna resta finché non accetti il quarto bis.</p>
				</article>
			</div>
			<div class="bigcard">
				<article>
					<h1>Iscriviti!</h1>
					<h2>Ti senti troppo magro? Lei è d’accordo.</h2>
					<a href="sign_up.jsp" class="button">Prenota ora</a>
					<p><small>(Prima che si offende)</small></p>
				</article>
			</div>
			<div>
			<!-- Recensioni -->
			</div>
			<div>
			<!-- FAQ -->
			</div>
			</div>
	</body>
</html>