package de.thb.service.impl;


import de.thb.data.KompBereich;
import de.thb.repository.api.KompBereichRepository;
import de.thb.repository.api.SchluesselKompRepository;
import de.thb.service.api.SchluesselKompService;

import javax.inject.Inject;
import java.util.Set;

public class SchluesselKompServiceImpl
implements SchluesselKompService {


	@Inject
	private SchluesselKompRepository schluessel;
	
	@Inject private KompBereichRepository kbri;
	
	/*
	 * (non-Javadoc)
	 * @see de.thb.fbi.project.koma.service.api.SchluesselKompService#findFindKompetenzBereiche(de.thb.fbi.project.koma.data.SchluesselKomp)
	 * @param die schluesselKompetenz
	 */
	//TODO: edit
	@Override
	public Set<KompBereich> findFindKompetenzBereiche(int aId) {
		return schluessel.findKompetenzBereiche(aId);
	}
	
	
}
