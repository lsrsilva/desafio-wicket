package br.com.lucasilva.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.lucasilva.util.HibernateUtil;

public class GenericDao<Entidade, PK> {
	protected Class<Entidade> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDao() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade) {
		// Pega uma sessão já aberta
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			
			transacao.commit();
		} catch (RuntimeException re) {
			if(transacao != null) 
				transacao.rollback();
			throw re;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			
			List<Entidade> resultado = consulta.list();
			
			return resultado;
		} catch(RuntimeException re) {
			throw re;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(PK pk) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(classe);
			
			// Equivalente à:
			// WHERE id = 1;
			consulta.add(Restrictions.idEq(pk));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			
			return resultado;
		} catch(RuntimeException re) {
			throw re;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(Entidade entidade) {
		// Pega uma sessão já aberta
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			
			transacao.commit();
		} catch (RuntimeException re) {
			if(transacao != null) 
				transacao.rollback();
			throw re;
		} finally {
			sessao.close();
		}
	}
	
	public void editar(Entidade entidade) {
		// Pega uma sessão já aberta
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transacao = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);

			transacao.commit();
		} catch (RuntimeException re) {
			if(transacao != null) 
				transacao.rollback();
			throw re;
		} finally {
			sessao.close();
		}
	}
	
	
}
