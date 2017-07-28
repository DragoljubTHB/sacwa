package de.thb.fbi.project.generic.controller.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import de.thb.fbi.project.generic.controller.api.GenericCRUD;
import de.thb.fbi.project.generic.data.NamedEntity;
import de.thb.fbi.project.generic.service.api.NamedEntityService;

public abstract class GenericCRUDCtrl<EntityType extends NamedEntity, ServiceType extends NamedEntityService<EntityType>>
		implements GenericCRUD<EntityType> {

	private ServiceType si;

	protected EntityType entityType;

	protected abstract EntityType getEntityType();

	protected abstract ServiceType getServiceType();

	@PostConstruct
	private void init() {
		entityType = getEntityType();
		si = getServiceType();
	}

	/*
	 * named CRUD Methods
	 */
	@Override
	public String create() {
		si.create(entityType);
		return (null);
	}

	@Override
	public String create(EntityType entityType) {
		si.create(entityType);
		return (null);
	}

	@Override
	public EntityType readById(int id) {
		return si.find(id);

	}

	@Override
	public List<EntityType> readAll() {
		return si.findAll();

	}

	@Override
	public String update(EntityType entityType) {
		si.update(entityType);
		return (null);
	}

	@Override
	public String deleteById(int id) {
		si.delete(id);
		return (null);
	}

	@Override
	public String delete(EntityType entityType) {
		si.delete(entityType);
		return (null);
	}

	@Override
	public String findByName(String aName) {
		// TODO
		return (null);
	}

	protected void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
