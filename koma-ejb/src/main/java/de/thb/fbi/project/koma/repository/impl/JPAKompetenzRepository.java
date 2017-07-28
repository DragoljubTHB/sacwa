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
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.repository.api.KompetenzRepository;

public class JPAKompetenzRepository extends JPANamedEntityRepository<Kompetenz> implements KompetenzRepository {

	@Inject
	private Logger logger;
	
	/**
	 *TODO: Kompetenz -> KompBereich -> List kompetenzen Z! bags
	 * workaround vs hibernate lazy collection bags exception
	 */
	@Override
	public List<KompBereich> findKompetenzBereicheByKompetenzId(int aId) {// Kompetenz
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<KompBereich> cq = cb.createQuery(KompBereich.class);

		Root<Kompetenz> kompetenzRoot = cq.from(Kompetenz.class);
		logger.info("find kompetenz with id {}", aId);
		cq.where(cb.equal(kompetenzRoot.get("id"), aId));

		Join<Kompetenz, KompBereich> kompetenzJoinBereiche = kompetenzRoot.join("kompBereich");

		CriteriaQuery<KompBereich> cqKB = cq.select(kompetenzJoinBereiche);

		TypedQuery<KompBereich> tq = em.createQuery(cqKB);
		logger.info("query executed: {}", tq.toString());

		List<KompBereich> kompBereichs = tq.getResultList();
		for (KompBereich kb : kompBereichs) {
			logger.info("fuer SchluesselKomp id {}, found kompBereich {}", aId, kb.getName());
		}
		return kompBereichs;
	}
}
