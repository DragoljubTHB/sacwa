package de.thb.fbi.project.generic.repository.impl;

import java.lang.reflect.ParameterizedType;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.data.NamedEntity;
import de.thb.fbi.project.generic.interceptors.RepositoryException;
import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;

public class JPANamedEntityRepository<NamedEntityType extends NamedEntity> 
	extends JPARepository<NamedEntityType>
	implements NamedEntityRepository<NamedEntityType>
{
	@Inject Logger logger;
	
	@SuppressWarnings("unchecked")
	@Override
	protected Class<NamedEntityType> initEntityType() {
		Class<?> cls = getClass();
		while (!(cls.getSuperclass() == null || cls.getSuperclass().equals(JPANamedEntityRepository.class))) {
			cls = cls.getSuperclass();
		}
		if (cls.getSuperclass() == null) {
			throw new RuntimeException("Unexpected exception occured");
		}
		Class<NamedEntityType> class1 = (Class<NamedEntityType>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0];
		return class1;
	}

	@Override
	public void create(NamedEntityType objectWithoutId) {
		if (checkIfNameExists(objectWithoutId.getName())) {
			throw new RepositoryException("Der Name ist bereits in Verwendung!");
		} else {
			super.create(objectWithoutId);
		}
	}

	@Override
	public boolean checkIfNameExists(String name) {
		logger.info("checkIfNameExists: "+name, entityType);
		
		CriteriaBuilder critBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> critQuery = critBuilder.createQuery(Long.class);
		Root<NamedEntityType> rootEntry = critQuery.from(entityType);
		critQuery.select(critBuilder.count(rootEntry));
		critQuery.where(critBuilder.equal(rootEntry.get("name"), name));

		TypedQuery<Long> query = em.createQuery(critQuery);

		return (query.getSingleResult() != 0);
		
	}
}
