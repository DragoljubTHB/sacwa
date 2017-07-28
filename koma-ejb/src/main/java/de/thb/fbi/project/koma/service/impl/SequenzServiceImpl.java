package de.thb.fbi.project.koma.service.impl;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.Sequenz;
import de.thb.fbi.project.koma.repository.impl.JPASequenzRepository;
import de.thb.fbi.project.koma.service.api.SequenzService;

public class SequenzServiceImpl extends NamedEntityServiceImpl<Sequenz, JPASequenzRepository>
		implements SequenzService {

	@Inject
	private JPASequenzRepository jpaSequenzRepository;

	@Override
	protected JPASequenzRepository getRepositoryType() {
		return jpaSequenzRepository;
	}

}
