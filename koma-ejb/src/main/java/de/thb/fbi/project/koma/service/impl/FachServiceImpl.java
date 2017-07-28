package de.thb.fbi.project.koma.service.impl;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.GenericServiceImpl;
import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.repository.impl.JPAFachRepository;
import de.thb.fbi.project.koma.service.api.FachService;

public class FachServiceImpl extends NamedEntityServiceImpl<Fach, JPAFachRepository> implements FachService {

	@Inject
	private JPAFachRepository jpaFachRepository;

	@Override
	protected JPAFachRepository getRepositoryType() {
		return jpaFachRepository;
	}

}
