package de.thb.service.impl;


import de.thb.data.Kompetenz;
import de.thb.repository.api.KompBereichRepository;
import de.thb.service.api.KompBereichService;

import javax.inject.Inject;
import java.util.Set;

public class KompBereichServiceImpl
		implements KompBereichService {

	@Inject
	private KompBereichRepository kbri;


	@Override
	public Set<Kompetenz> findKompetenzenByBereich(int aId) {
		return kbri.findKompetenzenByBereich(aId);

	}

}
