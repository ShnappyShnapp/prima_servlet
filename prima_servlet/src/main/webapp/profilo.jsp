<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%
    // Se non c'è un utente in sessione, rimando al form di iscrizione
    if (session.getAttribute("username") == null) {
        response.sendRedirect("sign_up.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profilo</title>
</head>
<body>
    <h1>Benvenuto, ${sessionScope.username}!</h1>
    <p>Nome: ${sessionScope.nome}</p>
    <p>Cognome: ${sessionScope.cognome}</p>
    <p>Età: ${sessionScope.eta}</p>
    <p>Email: ${sessionScope.email}</p>
</body>
</html>
