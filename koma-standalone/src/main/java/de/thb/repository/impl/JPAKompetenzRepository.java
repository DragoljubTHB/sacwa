package de.thb.repository.impl;

import de.thb.data.KompBereich;
import de.thb.data.Kompetenz;
import de.thb.repository.api.KompetenzRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class JPAKompetenzRepository implements KompetenzRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 *TODO: Kompetenz -> KompBereich -> List kompetenzen Z! bags
	 * workaround vs hibernate lazy collection bags exception
	 */
	@Override
	public Set<KompBereich> findKompetenzBereicheByKompetenzId(int aId) {// Kompetenz
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompBereich> cq = cb.createQuery(KompBereich.class);

		Root<Kompetenz> kompetenzRoot = cq.from(Kompetenz.class);
		cq.where(cb.equal(kompetenzRoot.get("id"), aId));

		Join<Kompetenz, KompBereich> kompetenzJoinBereiche = kompetenzRoot.join("kompBereich");

		CriteriaQuery<KompBereich> cqKB = cq.select(kompetenzJoinBereiche);

		TypedQuery<KompBereich> tq = em.createQuery(cqKB);

		return new HashSet<>(tq.getResultList());
	}
}
