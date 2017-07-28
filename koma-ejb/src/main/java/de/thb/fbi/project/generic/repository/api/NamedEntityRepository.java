package de.thb.fbi.project.generic.repository.api;

import de.thb.fbi.project.generic.data.NamedEntity;

public interface NamedEntityRepository<EntityType extends NamedEntity> 
	extends BaseRepository<EntityType>
{

	boolean checkIfNameExists(String name);
	

}
