package de.thb.fbi.project.koma.service.api;

import java.util.List;

import de.thb.fbi.project.generic.service.api.NamedEntityService;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;

public interface KompetenzService 
	extends NamedEntityService<Kompetenz>
{
	List<KompBereich> findKompetenzBereicheByKompetenzId(int aId);
}

