package it.exolab.mapper;

import org.apache.ibatis.annotations.Param;

import it.exolab.model.UtenteModel;

public interface UtenteMapper {
	public void insert(UtenteModel utente);
	public UtenteModel find(@Param("username") String username);
	public int countByEmail(@Param("email") String email);
	public int countByUsername(@Param("username") String username);
}
