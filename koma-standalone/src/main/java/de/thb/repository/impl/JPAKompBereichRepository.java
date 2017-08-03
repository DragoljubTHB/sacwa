package de.thb.repository.impl;

import de.thb.data.Fertigkeit;
import de.thb.data.KompBereich;
import de.thb.data.Kompetenz;
import de.thb.repository.api.KompBereichRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

public class JPAKompBereichRepository implements KompBereichRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<KompBereich> findByKomp(Kompetenz komp) {
		TypedQuery<KompBereich> query = em
				.createQuery("SELECT k " + "FROM KompBereich" + "WHERE k.Kompetenz = kompetenz", KompBereich.class);
		query.setParameter("kompetenz", komp);

		return new HashSet<>(query.getResultList());
	}

	@Override
	public Set<Fertigkeit> findBySchluesselKomp(Kompetenz komp) {
		TypedQuery<Fertigkeit> query = em.createQuery("SELECT k FROM KompBereich k  WHERE k.SchluesselKomp = :komp",
				Fertigkeit.class);
		query.setParameter("komp", komp);

		return new HashSet<>(query.getResultList());
	}

	@Override
	public Set<Kompetenz> findKompetenzenByBereich(int aId) {
		// TODO Auto-generated method stub
		return null;
	}
}
