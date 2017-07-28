package de.thb.fbi.project.koma.repository.impl;

import java.util.List;

import de.thb.fbi.project.generic.repository.impl.JPANamedEntityRepository;
import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.repository.api.FachRepository;

public class JPAFachRepository extends JPANamedEntityRepository<Fach> implements FachRepository {

	@Override
	public List<Fach> findByFertigkeit(Fertigkeit fertigkeit) {
		// TODO Auto-generated method stub
		return null;
	}

}
