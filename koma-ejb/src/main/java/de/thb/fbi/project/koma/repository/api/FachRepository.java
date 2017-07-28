package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.BaseRepository;
import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.data.Fertigkeit;

public interface FachRepository extends BaseRepository<Fach> {
	List<Fach> findByFertigkeit(Fertigkeit fertigkeit);
}
