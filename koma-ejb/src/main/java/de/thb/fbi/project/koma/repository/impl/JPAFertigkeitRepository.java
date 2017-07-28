package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.repository.api.FertigkeitRepository;

public class JPAFertigkeitRepository extends JPANamedEntityRepository<Fertigkeit> implements FertigkeitRepository {
	@Override
	public List<Fertigkeit> findByKompetenz(Kompetenz komp) {
		TypedQuery<Fertigkeit> query = em.createQuery("SELECT f FROM Fertigkeit f WHERE f.KompNiveau = :komp",
				Fertigkeit.class);
		query.setParameter("komp", komp);

		return query.getResultList();
	}

}
