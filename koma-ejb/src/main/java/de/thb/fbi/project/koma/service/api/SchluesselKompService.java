package de.thb.fbi.project.koma.service.api;

import java.util.List;

import de.thb.fbi.project.generic.service.api.NamedEntityService;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.SchluesselKomp;

public interface SchluesselKompService extends NamedEntityService<SchluesselKomp> {

	List<KompBereich> findFindKompetenzBereiche(int aId);

}
