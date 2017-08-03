package de.thb.service.impl;


import de.thb.repository.api.KlasseRepository;
import de.thb.service.api.KlassenstufenService;

import javax.inject.Inject;

public class KlassenstufenServiseImpl
		implements KlassenstufenService {

	@Inject
	private KlasseRepository jpaKlasseRepository;


}
