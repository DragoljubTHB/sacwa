package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.SchluesselKomp;

public interface SchluesselKompRepository extends NamedEntityRepository<SchluesselKomp> {

	List<KompBereich> findKompetenzBereiche(int aId);

	
	
}
