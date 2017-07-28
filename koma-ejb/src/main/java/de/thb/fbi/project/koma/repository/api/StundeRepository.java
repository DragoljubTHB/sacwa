package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Stunde;

public interface StundeRepository extends NamedEntityRepository<Stunde> {

	List<Stunde> findByFertigkeit(Fertigkeit fertigkeit);

}
