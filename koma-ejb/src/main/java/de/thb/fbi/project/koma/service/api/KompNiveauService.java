package de.thb.fbi.project.koma.service.api;

import de.thb.fbi.project.generic.service.api.NamedEntityService;
import de.thb.fbi.project.koma.data.KompNiveau;

public interface KompNiveauService extends NamedEntityService<KompNiveau> {

	void umbenenne(String newName, String oldName);

	void deleteByCriteria(KompNiveau kompNiveau);
	
	KompNiveau findByCriteria(String aString);

}
