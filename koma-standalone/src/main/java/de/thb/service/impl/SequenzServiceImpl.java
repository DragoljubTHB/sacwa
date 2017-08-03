package de.thb.service.impl;


import de.thb.repository.impl.JPASequenzRepository;
import de.thb.service.api.SequenzService;

import javax.inject.Inject;

public class SequenzServiceImpl
		implements SequenzService {

	@Inject
	private JPASequenzRepository jpaSequenzRepository;

}
