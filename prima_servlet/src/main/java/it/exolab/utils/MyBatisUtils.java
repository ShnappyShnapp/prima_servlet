package it.exolab.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	private static final SqlSessionFactory factory;

	private MyBatisUtils() {
	}

	static {
		// Legge mybatis-config.xml dal classpath (WEB-INF/classes).
		// Funziona anche dentro il WAR, a differenza di File/FileReader.
		try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException("Impossibile leggere mybatis-config.xml", e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
