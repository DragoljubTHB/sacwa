package de.thb.fbi.project.koma.service.impl;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.repository.api.KompNiveauRepository;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

public class KompNiveauServiceImpl extends NamedEntityServiceImpl<KompNiveau, KompNiveauRepository>
		implements KompNiveauService {

	@Inject
	private KompNiveauRepository knri;

	@Override
	protected KompNiveauRepository getRepositoryType() {

		return knri;
	}

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
