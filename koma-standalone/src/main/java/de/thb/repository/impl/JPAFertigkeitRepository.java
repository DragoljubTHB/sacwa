package de.thb.repository.impl;

import de.thb.data.Fertigkeit;
import de.thb.data.Kompetenz;
import de.thb.repository.api.FertigkeitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class JPAFertigkeitRepository implements FertigkeitRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Fertigkeit> findByKompetenz(Kompetenz komp) {
		TypedQuery<Fertigkeit> query = em.createQuery("SELECT f FROM Fertigkeit f WHERE f.KompNiveau = :komp",
				Fertigkeit.class);
		query.setParameter("komp", komp);

		return new HashSet<>(query.getResultList());
	}

}
