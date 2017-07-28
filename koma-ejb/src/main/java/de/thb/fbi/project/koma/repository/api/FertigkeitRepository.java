package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.BaseRepository;
import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Kompetenz;

public interface FertigkeitRepository extends NamedEntityRepository<Fertigkeit> {

	List<Fertigkeit> findByKompetenz(Kompetenz komp);

}
