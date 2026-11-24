package it.exolab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.exolab.dao.UtenteDAO;
import it.exolab.model.UtenteModel;
import it.exolab.service.UtenteService;

/**
 * Servlet implementation class modifyUserServlet
 */
@WebServlet("/modifyUserServlet")
public class modifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteService utenteService = new UtenteService();
	private final UtenteDAO utenteDAO = new UtenteDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("impostazioni.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    // 1. Recupero l'utente loggato: se non c'è, lo mando al login
	    UtenteModel utente = (UtenteModel) request.getSession().getAttribute("utenteLoggato");
	    if (utente == null) {
	        response.sendRedirect(request.getContextPath() + "/sign_in.jsp");
	        return;
	    }

	    String nome     = request.getParameter("nome");
	    String cognome  = request.getParameter("cognome");
	    String etaStr   = request.getParameter("eta");
	    String username = request.getParameter("username");

	    // 2. Validazione (gli errori tornano a impostazioni.jsp)
	    if (utenteService.isVuoto(nome) || utenteService.isVuoto(cognome)
	            || utenteService.isVuoto(etaStr) || utenteService.isVuoto(username)) {
	        utenteService.mostraErrore(request, response, "impostazioni.jsp", "Compila tutti i campi");
	        return;
	    }

	    Integer eta = utenteService.parseEta(etaStr);
	    if (eta == null) {
	        utenteService.mostraErrore(request, response, "impostazioni.jsp", "Età non valida");
	        return;
	    }

	    // 3. Controllo username solo se è cambiato (altrimenti trova sé stesso)
	    if (!username.trim().equals(utente.getUsername())
	            && utenteDAO.usernameEsistente(username.trim())) {
	        utenteService.mostraErrore(request, response, "impostazioni.jsp", "Questo username è già in uso");
	        return;
	    }

	    UtenteModel aggiornato;
	    try {
	        aggiornato = utenteService.modificaUtente(utenteDAO, utente, nome, cognome, eta, username);
	    } catch (Exception e) {
	        e.printStackTrace();
	        utenteService.mostraErrore(request, response, "impostazioni.jsp", "Errore durante la modifica");
	        return;
	    }

	    request.getSession().setAttribute("utenteLoggato", aggiornato);
	    response.sendRedirect(request.getContextPath() + "/profilo.jsp");
	}

}
