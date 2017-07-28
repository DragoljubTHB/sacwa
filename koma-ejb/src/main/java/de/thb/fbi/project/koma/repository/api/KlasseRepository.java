package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Klassenstufe;

public interface KlasseRepository extends NamedEntityRepository<Klassenstufe> {
	List<Klassenstufe> findByFertigkeit(Fertigkeit fertigkeit);
}
