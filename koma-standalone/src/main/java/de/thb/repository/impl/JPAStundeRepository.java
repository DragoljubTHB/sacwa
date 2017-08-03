package de.thb.repository.impl;

import de.thb.data.Fertigkeit;
import de.thb.data.Stunde;
import de.thb.repository.api.StundeRepository;

import java.util.Set;

public class JPAStundeRepository  implements StundeRepository {

	@Override
	public Set<Stunde> findByFertigkeit(Fertigkeit fertigkeit) {
		// TODO Auto-generated method stub
		return null;
	}

}
