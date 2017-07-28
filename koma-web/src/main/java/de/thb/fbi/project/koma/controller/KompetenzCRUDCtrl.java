package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.service.api.KompetenzService;

@Named
@RequestScoped
public class KompetenzCRUDCtrl
	extends GenericCRUDCtrl<Kompetenz, KompetenzService>
{

	private Kompetenz kompetenz = new Kompetenz();
	private KompBereich kompBereich = new KompBereich();

	@Inject private KompetenzService ksi;

	@Inject Logger logger;
	
	public String doCrate(String aName){
		kompetenz.setName(aName);
		kompBereich.setName(aName);
		kompetenz.setKompBereich(kompBereich);
		
		logger.info("attempt to create kompetenz {}",kompetenz.getName());
		ksi.create(kompetenz);
		logger.info("created" );
		
		return (null);
	}
	public String doFind(int id){
		//readById(id) --> FIXME: werft hibernate lazy bags exception
		ksi.findKompetenzBereicheByKompetenzId(id);
		return (null);
	}
	
	/*
	 * std
	 * 
	 */
	

	@Override
	protected Kompetenz getEntityType() {
		return kompetenz; 
		
	}

	@Override
	protected KompetenzService getServiceType() {
		return ksi;

	}
	
	public String create(Kompetenz kompetenz){
		ksi.create(kompetenz);
		return (null);
	}
	@Override
	public String create(){
		ksi.create(kompetenz);
		return (null);
	}
	
	
	/* std */
	
	public Kompetenz getKompetenz() {
		return kompetenz;
	}

	public void setKompetenz(Kompetenz kompetenz) {
		this.kompetenz = kompetenz;
	}
	


}
