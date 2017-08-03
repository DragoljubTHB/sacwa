package de.thb.repository.impl;

import de.thb.data.Fach;
import de.thb.data.Fertigkeit;
import de.thb.repository.api.FachRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

//@Stateless
public class JPAFachRepository implements FachRepository {
	@PersistenceContext
	private EntityManager em;


	@Override
	public Set<Fach> findByFertigkeit(Fertigkeit fertigkeit) {
		return null;
	}

	@Override
	public Set<Fach> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Fach> cq = cb.createQuery(Fach.class);
		Root<Fach> rootEntry = cq.from(Fach.class);
		CriteriaQuery<Fach> all = cq.select(rootEntry);
		TypedQuery<Fach> allQuery = em.createQuery(all);

		return new HashSet<>(allQuery.getResultList());
	}

	@Override
	public void create(Fach fach) {//todo: notnull
		em.persist(fach);
	}
}
