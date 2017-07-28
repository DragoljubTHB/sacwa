package de.thb.fbi.project.koma.service.impl;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.Stunde;
import de.thb.fbi.project.koma.repository.impl.JPAStundeRepository;
import de.thb.fbi.project.koma.service.api.StundeService;

public class StundeServiceImpl extends NamedEntityServiceImpl<Stunde, JPAStundeRepository> implements StundeService {

	@Inject
	private JPAStundeRepository jpaStundeRepository;

	@Override
	protected JPAStundeRepository getRepositoryType() {
		return jpaStundeRepository;

	}

}
