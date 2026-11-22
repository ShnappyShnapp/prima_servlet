package it.exolab.dao;

import org.apache.ibatis.session.SqlSession;

import it.exolab.mapper.UtenteMapper;
import it.exolab.model.UtenteModel;
import it.exolab.utils.MyBatisUtils;

public class UtenteDAO {

	public void insert(UtenteModel utente) {
		// la sessione viene chiusa anche se c'è un errore
		try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
			UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
			utenteMapper.insert(utente);
			session.commit();
		}
	}

	public UtenteModel find(String username) {
		try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
			UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
			return utenteMapper.find(username);
		}
	}
	
	public boolean emailEsistente(String email) {
		try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
			UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
			int check = utenteMapper.countByEmail(email);
			if(check > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean usernameEsistente(String username) {
		try (SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession()) {
			UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
			int check = utenteMapper.countByUsername(username);
			if(check > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
}
