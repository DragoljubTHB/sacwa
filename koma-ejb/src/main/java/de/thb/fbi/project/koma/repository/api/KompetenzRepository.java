package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;

public interface KompetenzRepository extends NamedEntityRepository<Kompetenz> {

	List<KompBereich> findKompetenzBereicheByKompetenzId(int aId);

}
