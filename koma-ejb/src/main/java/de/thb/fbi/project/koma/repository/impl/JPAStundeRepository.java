package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import javax.enterprise.inject.Default;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Stunde;
import de.thb.fbi.project.koma.repository.api.StundeRepository;

@Default
public class JPAStundeRepository extends JPANamedEntityRepository<Stunde> implements StundeRepository {

	@Override
	public List<Stunde> findByFertigkeit(Fertigkeit fertigkeit) {
		// TODO Auto-generated method stub
		return null;
	}

}
