package de.thb.service.impl;


import de.thb.repository.impl.JPAStundeRepository;
import de.thb.service.api.StundeService;

import javax.inject.Inject;

public class StundeServiceImpl
		implements StundeService {

	@Inject
	private JPAStundeRepository jpaStundeRepository;

}
