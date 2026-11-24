package it.exolab.web;

import java.io.IOException;

import it.exolab.dao.UtenteDAO;
import it.exolab.model.UtenteModel;
import it.exolab.service.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getUserServlet")
public class getUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UtenteService utenteService = new UtenteService();

	
	//mettiamo dinuovo il DAO per parlare con il db
	private final UtenteDAO utenteDAO = new UtenteDAO();
	
	public getUserServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Se qualcuno apre la servlet direttamente, lo rimandiamo al form
		response.sendRedirect("sign_in.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    String pssw = request.getParameter("pssw");

	    if (utenteService.isVuoto(username) || utenteService.isVuoto(email) || utenteService.isVuoto(pssw)) {
	        utenteService.mostraErrore(request, response, "sign_in.jsp", "Compila tutti i campi");
	        return;
	    }

	    UtenteModel utente;
	    try {
	        utente = utenteService.searchUtente(utenteDAO, username);
	    } catch (Exception e) {
	        e.printStackTrace();
	        utenteService.mostraErrore(request, response, "sign_in.jsp", "Errore durante il Login: " + e.getMessage());
	        return;
	    }

	    if (utente == null) {
	        utenteService.mostraErrore(request, response, "sign_in.jsp", "Utente non registrato");
	        return;
	    }

	    if (!utenteService.checkEmailPassword(utente, email, pssw)) {
	        utenteService.mostraErrore(request, response, "sign_in.jsp", "Email o password errate");
	        return;
	    }

	    request.getSession().setAttribute("utenteLoggato", utente);
	    response.sendRedirect("profilo.jsp");
	}
}
