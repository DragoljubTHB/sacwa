package de.thb.fbi.project.koma.service.impl;

import java.util.List;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.repository.api.KompBereichRepository;
import de.thb.fbi.project.koma.service.api.KompBereichService;

public class KompBereichServiceImpl extends NamedEntityServiceImpl<KompBereich, KompBereichRepository>
		implements KompBereichService {

	@Inject
	private KompBereichRepository kbri;

	@Override
	protected KompBereichRepository getRepositoryType() {
		return kbri;

	}

	@Override
	public List<Kompetenz> findKompetenzenByBereich(int aId) {
		return kbri.findKompetenzenByBereich(aId);

	}

}
