package de.thb.service.impl;


import de.thb.data.KompNiveau;
import de.thb.repository.api.KompNiveauRepository;
import de.thb.service.api.KompNiveauService;

import javax.inject.Inject;

public class KompNiveauServiceImpl
		implements KompNiveauService {

	@Inject
	private KompNiveauRepository knri;

	@Override
	public void umbenenne(String newName, String oldName) {
		knri.umbenenne(newName, oldName);

	}

	@Override
	public void deleteByCriteria(KompNiveau kompNiveau) {
		knri.deleteByCriteria(kompNiveau);

	}

	@Override
	public KompNiveau findByCriteria(String aString) {
		return knri.findByCriteria(aString);
	}

}
