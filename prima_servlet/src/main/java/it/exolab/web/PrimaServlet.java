//La uso al posto di un controller praticamente

package it.exolab.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import it.exolab.dao.UtenteDAO;
import it.exolab.model.UtenteModel;
import it.exolab.service.UtenteService;


/**
 * Servlet implementation class PrimaServlet
 */
@WebServlet("/PrimaServlet")
public class PrimaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteService utenteService = new UtenteService();

	// Il DAO si occupa di parlare col database (tramite MyBatis)
	private final UtenteDAO utenteDAO = new UtenteDAO();

	public PrimaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Se qualcuno apre la servlet direttamente, lo rimandiamo al form
		response.sendRedirect("sign_up.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 1. Leggo i parametri
		String nome     = request.getParameter("nome");
		String cognome  = request.getParameter("cognome");
		String etaStr   = request.getParameter("eta");
		String username = request.getParameter("username");
		String email    = request.getParameter("email");
		String pssw     = request.getParameter("pssw"); // deve essere uguale al name="pssw" del form

		// 2. Controllo minimo che i campi non siano vuoti
		if (utenteService.isVuoto(nome) || utenteService.isVuoto(cognome) || utenteService.isVuoto(etaStr)
				|| utenteService.isVuoto(username) || utenteService.isVuoto(email) || utenteService.isVuoto(pssw)) {
			utenteService.mostraErrore(request, response, "sign_up.jsp", "Compila tutti i campi");
			return;
		}

		Integer eta = utenteService.parseEta(etaStr);
		if (eta == null) {
		    utenteService.mostraErrore(request, response, "sign_up.jsp", "Età non valida");
		    return;   // ← fondamentale
		}

		// 3. Metto i dati dentro il model
		UtenteModel utente = new UtenteModel();
		utente.setNome(nome);
		utente.setCognome(cognome);
		utente.setEta(eta);
		utente.setUsername(username);
		utente.setEmail(email);
		utente.setPssw(pssw);
		
		//Controllo che l'email e il nome utente siano univoci
		if (utenteDAO.emailEsistente(email)) {
			utenteService.mostraErrore(request, response, "sign_up.jsp", "Questa email è già registrata");
		    return;
		}
		if (utenteDAO.usernameEsistente(username)) {
			utenteService.mostraErrore(request, response, "sign_up.jsp", "Questo username è già in uso");
		    return;
		}

		// 4. Lo passo al DAO, che lo salva nel database
		try {
		    utenteService.registraUtente(utenteDAO, utente);
		} catch (Exception e) {
		    e.printStackTrace();
		    utenteService.mostraErrore(request, response, "sign_up.jsp", "Errore durante la registrazione");
		    return;
		}

		// 5. Se è tutto ok salvo l'utente in sessione e vado al profilo
		HttpSession session = request.getSession();
		session.setAttribute("nome", nome);
		session.setAttribute("cognome", cognome);
		session.setAttribute("eta", eta);
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("utente", utente);
		
		request.getSession().setAttribute("utenteLoggato", utente);
		response.sendRedirect(request.getContextPath() + "/profilo.jsp");
	}
}
