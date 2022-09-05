package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class GenericDaoImpl implements GenericDao {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public Object save(Object obj) {
		return entityManager.merge(obj);
	}

	@Override
	public <E> E fetchById(Class<E> clazz, Object pk) {
		return entityManager.find(clazz, pk);
	}

	@Override
	public <E> List<E> fetchAll(Class<E> clazz) {
		String q = "select obj from "+clazz.getName()+" as obj";
		return entityManager.createQuery(q).getResultList();
	}

	@Override
	public void delete(Object obj) {
		entityManager.remove(obj);
	}

}
