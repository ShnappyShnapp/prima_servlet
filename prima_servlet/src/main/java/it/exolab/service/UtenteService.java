package it.exolab.service;

import java.io.IOException;

import it.exolab.dao.UtenteDAO;
import it.exolab.model.UtenteModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UtenteService {

    public boolean isVuoto(String s) {
        return s == null || s.trim().isEmpty();
    }

    public void mostraErrore(HttpServletRequest request, HttpServletResponse response,
            String pagina, String messaggio) throws ServletException, IOException {
        request.setAttribute("errore", messaggio);
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    // restituisce l'utente trovato (o null se non esiste)
    public UtenteModel searchUtente(UtenteDAO utenteDAO, String username) throws Exception {
        return utenteDAO.find(username.trim());
    }

    // true se email e password corrispondono
    public boolean checkEmailPassword(UtenteModel utente, String email, String pssw) {
        return utente.getEmail().equalsIgnoreCase(email.trim())
            && utente.getPssw().equals(pssw);
    }
    
    //check eta
    // restituisce l'età, oppure null se non è un numero valido
    public Integer parseEta(String etaStr) {
        try {
            int eta = Integer.parseInt(etaStr.trim());
            return (eta > 0 && eta < 130) ? eta : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
 // salva l'utente; se qualcosa va storto l'eccezione arriva al chiamante
    public void registraUtente(UtenteDAO utenteDAO, UtenteModel utente) throws Exception {
        utenteDAO.insert(utente);
    }
    
 // restituisce l'utente aggiornato; l'originale non viene toccato
    public UtenteModel modificaUtente(UtenteDAO utenteDAO, UtenteModel originale,
            String nome, String cognome, int eta, String username) throws Exception {
        UtenteModel modificato = new UtenteModel();
        modificato.setEmail(originale.getEmail());   // serve per la WHERE
        modificato.setPssw(originale.getPssw());
        modificato.setId(originale.getId());
        modificato.setNome(nome.trim());
        modificato.setCognome(cognome.trim());
        modificato.setEta(eta);
        modificato.setUsername(username.trim());

        int righe = utenteDAO.update(modificato);
        if (righe == 0) {
            throw new Exception("Nessun utente trovato da modificare");
        }
        return modificato;
    }
}
