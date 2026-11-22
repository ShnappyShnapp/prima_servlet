package it.exolab.mapper;

import org.apache.ibatis.annotations.Param;

import it.exolab.model.UtenteModel;

public interface UtenteMapper {
	public void insert(UtenteModel utente);
	public UtenteModel find(@Param("email") String email, @Param("pssw") String pssw);
	public int countByEmail(@Param("email") String email);
	public int countByUsername(@Param("username") String username);
}
