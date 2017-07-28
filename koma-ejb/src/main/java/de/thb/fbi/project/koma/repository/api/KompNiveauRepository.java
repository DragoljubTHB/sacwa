package de.thb.fbi.project.koma.repository.api;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.KompNiveau;

public interface KompNiveauRepository extends NamedEntityRepository<KompNiveau> {

	void umbenenne(String newName, String oldName);

	void deleteByCriteria(KompNiveau kompNiveau);

	KompNiveau findByCriteria(String aString);

}
