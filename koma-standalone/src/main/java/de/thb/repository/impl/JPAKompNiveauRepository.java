package de.thb.repository.impl;

import de.thb.data.KompNiveau;
import de.thb.repository.api.KompNiveauRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;


public class JPAKompNiveauRepository implements KompNiveauRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public KompNiveau findByCriteria(String aString) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompNiveau> criteriaQuery = cb.createQuery(KompNiveau.class);
		Root<KompNiveau> kompNiveauRoot = criteriaQuery.from(KompNiveau.class);

		criteriaQuery.select(kompNiveauRoot).where(cb.equal(kompNiveauRoot.get("name"), aString));

		return em.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public void update(KompNiveau kompNiveau) {
		if(find(kompNiveau.getId()) != null){
			em.merge(kompNiveau);
		}
	}
	@Override
	public KompNiveau find(int id) {
		return em.find(KompNiveau.class, id);
	}

	@Override
	public void umbenenne(String newName, String oldName) {
		KompNiveau kn = new KompNiveau();
		kn = findByCriteria(oldName);
		kn.setName(newName);
		update(kn);
	}

	@Override
	public void deleteByCriteria(KompNiveau kompNiveau) {
		KompNiveau kn = new KompNiveau();
		kn = findByCriteria(kompNiveau.getName());
		if (kn != null) {
			em.remove(kn);
		}
	}


}
