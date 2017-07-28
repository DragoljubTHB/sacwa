package de.thb.fbi.project.generic.repository.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.data.GenericEntity;
import de.thb.fbi.project.generic.interceptors.ConvertJPAExceptions;
import de.thb.fbi.project.generic.repository.api.BaseRepository;


@ConvertJPAExceptions(message = "Name schon vorhanden")
public abstract class JPARepository<T extends GenericEntity> implements BaseRepository<T> {
	@Inject
	private Logger logger;

	protected final Class<T> entityType;

	
	protected JPARepository() {
		entityType=initEntityType();
	}
	@SuppressWarnings("unchecked")
	protected Class<T> initEntityType() {
		Class<?> cls = getClass();
		while (!(cls.getSuperclass() == null || cls.getSuperclass().equals(JPARepository.class))) {
			cls = cls.getSuperclass();
		}
		if (cls.getSuperclass() == null) {
			throw new RuntimeException("Unexpected exception occured");
		}

		return ((Class<T>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0]);
	}
//	protected JPARepository(Class<T> entity){
//		entityType = entity;
//	}

	@PersistenceContext
	protected EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#create(T)
	 */

	@Override
	public void create(T objectWithoutId) {
		logger.info("create {}", entityType);
		em.persist(objectWithoutId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#delete(int)
	 */
	@Override
	public void delete(int id) {
		logger.info("delete findBy ID {}", entityType);
		T removeObject = find(id);
		if (removeObject != null) {
			em.remove(removeObject);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#delete(T)
	 */
	@Override
	public void delete(@NotNull T myEntity) {
		logger.info("delete {}", entityType);
		delete(myEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#update(T)
	 */
	@Override
	public void update(@NotNull T myEntity) {
		logger.info("updating entety: {}", entityType);
		if(find(myEntity.getId()) != null){
			em.merge(myEntity);
		}	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#find(int)
	 */
	@Override
	public T find(int id) {
		logger.info("find {}", entityType);
		return em.find(entityType, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see repository.GenericServiceInterface#findAll()
	 */

	@Override
	public List<T> findAll() {
		logger.info("finding all {}", entityType);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityType);
		Root<T> rootEntry = cq.from(entityType);
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		return allQuery.getResultList();

	}


}
