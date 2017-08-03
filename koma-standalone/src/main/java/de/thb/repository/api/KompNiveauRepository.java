package de.thb.repository.api;

import de.thb.data.KompNiveau;

public interface KompNiveauRepository  {
	void update(KompNiveau kompNiveau);

	KompNiveau find(int id);

	void umbenenne(String newName, String oldName);

	void deleteByCriteria(KompNiveau kompNiveau);

	KompNiveau findByCriteria(String aString);

}
