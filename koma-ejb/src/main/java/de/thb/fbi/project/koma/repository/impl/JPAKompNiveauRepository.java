package de.thb.fbi.project.koma.repository.impl;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.repository.api.KompNiveauRepository;

public class JPAKompNiveauRepository extends JPANamedEntityRepository<KompNiveau> implements KompNiveauRepository {

	@Inject
	private Logger logger;

	/**
	 * "select k from KompNiveau k where k.name = '" + name +"'" CriteriaQuery
	 * bringt entkopplung zwischen JPA und DB
	 * 
	 * @param aString
	 * @return the found entity
	 */
	@Override
	public KompNiveau findByCriteria(String aString) {
		logger.info("-------> findByCriteria {}", aString);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompNiveau> criteriaQuery = cb.createQuery(KompNiveau.class);
		Root<KompNiveau> kompNiveauRoot = criteriaQuery.from(KompNiveau.class);

		criteriaQuery.select(kompNiveauRoot).where(cb.equal(kompNiveauRoot.get("name"), aString));

		return em.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public void umbenenne(String newName, String oldName) {
		logger.info("-------> umbenenne {} fur {}", newName, oldName);
		KompNiveau kn = new KompNiveau();
		kn = findByCriteria(oldName);
		kn.setName(newName);
		update(kn);
	}

	@Override
	public void deleteByCriteria(KompNiveau kompNiveau) {
		KompNiveau kn = new KompNiveau();
		kn = findByCriteria(kompNiveau.getName());
		logger.info("-------> found {}", kn.toString());
		if (kn != null) {
			em.remove(kn);
		}
		logger.info("-------> removed {}", kn.toString());
	}

}
