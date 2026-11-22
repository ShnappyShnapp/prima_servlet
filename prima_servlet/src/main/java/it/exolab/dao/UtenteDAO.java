package it.exolab.dao;

import org.apache.ibatis.session.SqlSession;

import it.exolab.mapper.UtenteMapper;
import it.exolab.model.UtenteModel;
import it.exolab.utils.MyBatisUtils;

public class UtenteDAO {
	public void insert(UtenteModel utente) {
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
		utenteMapper.insert(utente);
		session.commit();
		session.close();
	}
	
	public UtenteModel find (String email, String pssw) {
		UtenteModel utente = null;
		SqlSession session = MyBatisUtils.getSqlSessionFactory().openSession();
		UtenteMapper utenteMapper = session.getMapper(UtenteMapper.class);
		utente = utenteMapper.find(email, pssw);
		session.close();
		return utente;
	}
}
