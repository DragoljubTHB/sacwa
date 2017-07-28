package de.thb.fbi.project.generic.controller.api;

import java.util.List;

import de.thb.fbi.project.generic.data.NamedEntity;

public interface GenericCRUD<EntityType extends NamedEntity> {
	String create();

	String create(EntityType entityType);

	EntityType readById(int id);

	List<EntityType> readAll();

	String update(EntityType entityType);

	String deleteById(int id);

	String delete(EntityType entityType);
	
	String findByName(String aName);
}
