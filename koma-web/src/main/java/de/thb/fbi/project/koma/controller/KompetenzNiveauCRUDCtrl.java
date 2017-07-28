package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

@Named
@RequestScoped
public class KompetenzNiveauCRUDCtrl extends GenericCRUDCtrl<KompNiveau, KompNiveauService> {

	@Inject private Logger logger;
	
	@Inject private KompNiveauService knsi;

	private KompNiveau kompNiveau;
	
	public String doCreate(String aName, int rang){
		kompNiveau = new KompNiveau();
		kompNiveau.setName(aName);
		kompNiveau.setRang(rang);
		logger.info("attempt to create ");
		create(kompNiveau);
		logger.info("created");
		
		return (null);
	}
	
	@Override
	protected KompNiveau getEntityType() {
		return kompNiveau;
	}

	@Override
	protected KompNiveauService getServiceType() {
		return knsi;
	}
	
	
}
