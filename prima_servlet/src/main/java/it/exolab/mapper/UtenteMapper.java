package it.exolab.mapper;

import it.exolab.model.UtenteModel;

public interface UtenteMapper {
	public void insert(UtenteModel utente);
	public UtenteModel find (String email, String pssw);
}
