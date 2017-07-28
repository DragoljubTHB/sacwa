package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.repository.api.SchluesselKompRepository;

public class JPASchluesselKompRepository extends JPANamedEntityRepository<SchluesselKomp> implements SchluesselKompRepository {

	@Inject
	private Logger logger;
	
	/**
	 * workaround vs hibernate lazy collection bags exception
	 */
	@Override
	public List<KompBereich> findKompetenzBereiche(int aId) {// bySchluesselKompetenz
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompBereich> cq = cb.createQuery(KompBereich.class);

		Root<SchluesselKomp> schluesselKompRoot = cq.from(SchluesselKomp.class);
		logger.info("find schluesselKomp with id {}", aId);
		cq.where(cb.equal(schluesselKompRoot.get("id"), aId));

		Join<SchluesselKomp, KompBereich> schluesselJoinBereiche = schluesselKompRoot.join("kompBereiche");

		CriteriaQuery<KompBereich> cqKB = cq.select(schluesselJoinBereiche);

		TypedQuery<KompBereich> tq = em.createQuery(cqKB);
		logger.info("query executed: {}", tq.toString());

		List<KompBereich> kompBereichs = tq.getResultList();
		for (KompBereich kb : kompBereichs) {
			logger.info("fuer SchluesselKomp id {}, found kompBereich {}", aId, kb.getName());
		}
		return kompBereichs;
	}

}