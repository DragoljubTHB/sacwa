package de.thb.service.impl;

import de.thb.repository.api.FertigkeitRepository;
import de.thb.repository.impl.JPAFertigkeitRepository;
import de.thb.service.api.FertigkeitService;

import javax.inject.Inject;

public class FertigkeitServiceImpl
		implements FertigkeitService {
	@Inject
	private FertigkeitRepository jPAFertigkeitRepository;


}
