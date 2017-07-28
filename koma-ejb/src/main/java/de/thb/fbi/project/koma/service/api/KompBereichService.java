package de.thb.fbi.project.koma.service.api;

import java.util.List;

import de.thb.fbi.project.generic.service.api.NamedEntityService;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;

public interface KompBereichService extends NamedEntityService<KompBereich>{

	List<Kompetenz> findKompetenzenByBereich(int aId);
}
