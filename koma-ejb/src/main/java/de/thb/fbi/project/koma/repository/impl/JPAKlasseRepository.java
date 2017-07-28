package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Klassenstufe;
import de.thb.fbi.project.koma.repository.api.KlasseRepository;

public class JPAKlasseRepository extends JPANamedEntityRepository<Klassenstufe> implements KlasseRepository {

	@Override
	public List<Klassenstufe> findByFertigkeit(Fertigkeit fertigkeit) {
		// TODO Auto-generated method stub
		return null;
	}

}
