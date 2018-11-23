package br.com.lucasilva.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch(HibernateException he) {
			System.err.println("Falha na criação da SessionFactory." + he);
			throw new ExceptionInInitializerError(he);
		} catch(Throwable th) {
			System.err.println("Falha na criação da SessionFactory." + th);
			throw new ExceptionInInitializerError(th);
		}
	}
	
	// método que será utilizado pelo Dao para criar a fábrica de sessões
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
