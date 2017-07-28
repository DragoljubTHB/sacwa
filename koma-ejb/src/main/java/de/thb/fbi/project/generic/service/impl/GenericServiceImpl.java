package de.thb.fbi.project.generic.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import de.thb.fbi.project.generic.data.GenericEntity;
import de.thb.fbi.project.generic.repository.api.BaseRepository;
import de.thb.fbi.project.generic.service.api.GenericService;

@Transactional
public abstract class GenericServiceImpl<EntityType extends GenericEntity, RepositoryType extends BaseRepository<EntityType>>
		implements GenericService<EntityType> {

	protected RepositoryType bri;

	protected abstract RepositoryType getRepositoryType();

	@PostConstruct
	public void init() {
		bri = getRepositoryType();
	}

	@Override
	public void create(EntityType entityType) {
		bri.create(entityType);

	}

	@Override
	public void delete(int id) {
		bri.delete(id);

	}

	@Override
	public void delete(EntityType objectWithoutId) {
		bri.delete(objectWithoutId);

	}

	@Override
	public void update(EntityType objectWithoutId) {
		bri.update(objectWithoutId);

	}

	@Override
	public EntityType find(int id) {
		return bri.find(id);

	}

	@Override
	public List<EntityType> findAll() {
		return bri.findAll();
	}
	
}
