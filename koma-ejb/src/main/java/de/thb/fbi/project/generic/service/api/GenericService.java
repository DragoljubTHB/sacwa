package de.thb.fbi.project.generic.service.api;

import java.util.List;

import de.thb.fbi.project.generic.data.GenericEntity;

public interface GenericService 
	<EntityType extends GenericEntity>
{
	void create(EntityType entityType);
	void delete(int id);
	void delete(EntityType objectWithoutId);
	void update(EntityType objectWithoutId);
	EntityType find(int id);
	List<EntityType> findAll();
	
}
