package de.thb.service.impl;

import de.thb.data.KompBereich;
import de.thb.repository.api.KompetenzRepository;
import de.thb.service.api.KompetenzService;

import javax.inject.Inject;
import java.util.Set;

public class KompetenzServiceImpl
	implements KompetenzService
{

	@Inject
	private KompetenzRepository kri;

	@Override
	public Set<KompBereich> findKompetenzBereicheByKompetenzId(int aId) {
		return kri.findKompetenzBereicheByKompetenzId(aId);
	}



}
