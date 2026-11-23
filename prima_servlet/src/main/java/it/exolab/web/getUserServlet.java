package it.exolab.web;

import java.io.IOException;

import it.exolab.dao.UtenteDAO;
import it.exolab.model.UtenteModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getUserServlet")
public class getUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//mettiamo dinuovo il DAO per parlare con il db
	private final UtenteDAO utenteDAO = new UtenteDAO();
	
	public getUserServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Se qualcuno apre la servlet direttamente, lo rimandiamo al form
		response.sendRedirect("sign_in.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pssw = request.getParameter("pssw");
		
		//controllo che i campi non siano vuoti
		if (isVuoto(username) || isVuoto(email) || isVuoto(pssw)) {
			mostraErrore(request, response, "Compila tutti i campi");
			return;
		}
		
		UtenteModel utente;
		
		//cerco l'utente, se è inesistente, mando errore
		try {
			utente = utenteDAO.find(username.trim());
		} catch (Exception e) {//in caso c'è un problema da mybatis
			e.printStackTrace();
			mostraErrore(request, response, "Errore durante il Login: " + e.getMessage());
			return;
		}
		
		//check se l'utente esiste
		if (utente == null) {
	        mostraErrore(request, response, "Utente non registrato");
	        return;
	    }
		
		// utente trovato, ma email o password non corrispondono
	    if (!utente.getEmail().equalsIgnoreCase(email.trim()) || !utente.getPssw().equals(pssw)) {
	        mostraErrore(request, response, "Email o password errate");
	        return;
	    }
	    
	 // login riuscito: salvo l'utente in sessione e lo mando a una pagina
	    request.getSession().setAttribute("utenteLoggato", utente);
		
		Cookie cookie = new Cookie("id", utente.getEmail());
		cookie.setMaxAge(3600);
		
		response.addCookie(cookie);
	    response.sendRedirect("profilo.jsp");
	}
	
	// Rimando al form mostrando il messaggio di errore
		private void mostraErrore(HttpServletRequest request, HttpServletResponse response, String messaggio)
				throws ServletException, IOException {
			request.setAttribute("errore", messaggio);
			request.getRequestDispatcher("sign_in.jsp").forward(request, response);
		}
	
	// Il check se la stringa è vuota
			private boolean isVuoto(String s) {
				return s == null || s.trim().isEmpty();
			}
}
