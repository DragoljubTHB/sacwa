package de.thb.fbi.project.koma.service.impl;

import javax.inject.Inject;

import de.thb.fbi.project.generic.service.impl.NamedEntityServiceImpl;
import de.thb.fbi.project.koma.data.Klassenstufe;
import de.thb.fbi.project.koma.repository.impl.JPAKlasseRepository;
import de.thb.fbi.project.koma.service.api.KlassenstufenService;

public class KlassenstufenServiseImpl extends NamedEntityServiceImpl<Klassenstufe, JPAKlasseRepository>
		implements KlassenstufenService {

	@Inject
	private JPAKlasseRepository jpaKlasseRepository;

	@Override
	protected JPAKlasseRepository getRepositoryType() {
		return jpaKlasseRepository;
	}

}
