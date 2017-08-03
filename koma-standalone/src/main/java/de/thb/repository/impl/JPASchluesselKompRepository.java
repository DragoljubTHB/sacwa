package de.thb.repository.impl;

import de.thb.data.KompBereich;
import de.thb.data.SchluesselKomp;
import de.thb.repository.api.SchluesselKompRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class JPASchluesselKompRepository implements SchluesselKompRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * workaround vs hibernate lazy collection bags exception
	 */
	@Override
	public Set<KompBereich> findKompetenzBereiche(int aId) {// bySchluesselKompetenz
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompBereich> cq = cb.createQuery(KompBereich.class);

		Root<SchluesselKomp> schluesselKompRoot = cq.from(SchluesselKomp.class);
		cq.where(cb.equal(schluesselKompRoot.get("id"), aId));

		Join<SchluesselKomp, KompBereich> schluesselJoinBereiche = schluesselKompRoot.join("kompBereiche");

		CriteriaQuery<KompBereich> cqKB = cq.select(schluesselJoinBereiche);

		TypedQuery<KompBereich> tq = em.createQuery(cqKB);

		return new HashSet<>(tq.getResultList());
	}

}