package de.thb.service.api;


import de.thb.data.KompNiveau;

public interface KompNiveauService  {

	void umbenenne(String newName, String oldName);

	void deleteByCriteria(KompNiveau kompNiveau);
	
	KompNiveau findByCriteria(String aString);

}
