package de.thb.repository.impl;

import de.thb.data.Fertigkeit;
import de.thb.data.Klassenstufe;
import de.thb.repository.api.KlasseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

public class JPAKlasseRepository implements KlasseRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Klassenstufe> findByFertigkeit(Fertigkeit fertigkeit) {
		// TODO Auto-generated method stub
		return null;
	}

}
