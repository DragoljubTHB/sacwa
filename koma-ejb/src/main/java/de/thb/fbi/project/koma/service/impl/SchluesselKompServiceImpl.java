package de.thb.fbi.project.koma.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.repository.api.KompBereichRepository;
import de.thb.fbi.project.koma.repository.api.SchluesselKompRepository;
import de.thb.fbi.project.koma.service.api.SchluesselKompService;

public class SchluesselKompServiceImpl 
extends NamedEntityServiceImpl<SchluesselKomp, SchluesselKompRepository>
implements SchluesselKompService {

	@Inject private Logger logger;
	
	@Inject private SchluesselKompRepository schluessel;
	
	@Inject private KompBereichRepository kbri;
	
	@Override
	protected SchluesselKompRepository getRepositoryType() {
		return schluessel;
	}

	/*
	 * (non-Javadoc)
	 * @see de.thb.fbi.project.koma.service.api.SchluesselKompService#findFindKompetenzBereiche(de.thb.fbi.project.koma.data.SchluesselKomp)
	 * @param die schluesselKompetenz
	 */
	//TODO: edit
	@Override
	public List<KompBereich> findFindKompetenzBereiche(int aId) {
		schluessel.findKompetenzBereiche(aId);
		
		return null;
	}
	
	
}
