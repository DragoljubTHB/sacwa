package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.repository.api.KompBereichRepository;

public class JPAKompBereichRepository extends JPANamedEntityRepository<KompBereich> implements KompBereichRepository {

	@Override
	public List<KompBereich> findByKomp(Kompetenz komp) {
		TypedQuery<KompBereich> query = em
				.createQuery("SELECT k " + "FROM KompBereich" + "WHERE k.Kompetenz = kompetenz", KompBereich.class);
		query.setParameter("kompetenz", komp);

		return query.getResultList();
	}

	@Override
	public List<Fertigkeit> findBySchluesselKomp(Kompetenz komp) {
		TypedQuery<Fertigkeit> query = em.createQuery("SELECT k FROM KompBereich k  WHERE k.SchluesselKomp = :komp",
				Fertigkeit.class);
		query.setParameter("komp", komp);

		return query.getResultList();
	}

	@Override
	public List<Kompetenz> findKompetenzenByBereich(int aId) {
		// TODO Auto-generated method stub
		return null;
	}
}
