package de.thb.repository.api;

import de.thb.data.Fertigkeit;
import de.thb.data.Stunde;

import java.util.Set;

public interface StundeRepository {

	Set<Stunde> findByFertigkeit(Fertigkeit fertigkeit);

}
