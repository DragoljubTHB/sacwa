package de.thb.repository.api;

import de.thb.data.Fertigkeit;
import de.thb.data.Klassenstufe;

import java.util.Set;

public interface KlasseRepository {
	Set<Klassenstufe> findByFertigkeit(Fertigkeit fertigkeit);
}
