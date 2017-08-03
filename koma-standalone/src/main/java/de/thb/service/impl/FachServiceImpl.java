package de.thb.service.impl;


import de.thb.repository.api.FachRepository;
import de.thb.service.api.FachService;

import javax.inject.Inject;

public class FachServiceImpl implements FachService {

	@Inject
	private FachRepository jpaFachRepository;

}
