package br.com.helio.jsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.helio.jsf.jpautil.JPAUtil;

public class DaoGeneric<T> {
	
	public void salvar(T entidade) {
		EntityManager entity = JPAUtil.getEntityManager();
		EntityTransaction trans = entity.getTransaction();
		trans.begin();
		
		entity.persist(entidade);
		
		trans.commit();
		entity.close();
		
	}

	public T merge(T entidade) {
		EntityManager entity = JPAUtil.getEntityManager();
		EntityTransaction trans = entity.getTransaction();
		trans.begin();
		
		T retorno = entity.merge(entidade);
		
		trans.commit();
		entity.close();
		
		return retorno;
		
	}
	
	public void deletePorId(T entidade) {
		EntityManager entity = JPAUtil.getEntityManager();
		EntityTransaction trans = entity.getTransaction();
		trans.begin();
		
		Object id = JPAUtil.getPrimaryKey(entidade);
		entity.createQuery("DELETE FROM "+entidade.getClass().getCanonicalName()+" WHERE id="+id).executeUpdate();
		
		trans.commit();
		entity.close();
		
	}
	
	
	public List<T> getListEntity(Class<T> entidade){
		EntityManager entity = JPAUtil.getEntityManager();
		EntityTransaction trans = entity.getTransaction();
		trans.begin();
		
		List<T> retorno = entity.createQuery("from "+entidade.getName()).getResultList();
		trans.commit();
		entity.close();
		
		return retorno;
	}
	
	
}
