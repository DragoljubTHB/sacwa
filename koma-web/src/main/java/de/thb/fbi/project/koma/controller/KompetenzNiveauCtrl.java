package de.thb.fbi.project.koma.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

import org.slf4j.Logger;

import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

@Named
@RequestScoped
public class KompetenzNiveauCtrl{

	private KompNiveau kompNiveau;

	@Size(min = 1, message = "{kompetenzniveauCtrl.oldName.sizeString}")
	private String oldName;

	@Size(min = 1, message = "{kompetenzniveauCtrl.oldName.sizeString}")
	private String newName;

	@Inject
	private Logger logger;

	@Inject
	private KompNiveauService knsi;

	
	
	@Inject FacesContext facesContext;
	
	

	public String create() {
		
		try{
			logger.info("-> create {}", kompNiveau.toString());
			knsi.create(kompNiveau);	
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "created", "creation successfull"));
		}catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
		
		return (null);
	}

	public String delete() {
		logger.info("-> delete {}", kompNiveau.toString());
		knsi.deleteByCriteria(kompNiveau);
		return (null);
	}

	public String umbenenne() {
		logger.info("-> umbenenne");
		knsi.umbenenne(newName, oldName);
		return (null);
	}
	public String findByName(String aName){
		logger.info("find by Name{}",aName);

		return (null);
	}
	public String findByCriteria(String aString){
		logger.info("find by Criteria {}",aString);
		KompNiveau k = knsi.findByCriteria(aString);
		logger.info("found {}", k.toString());
		return (null);
	}

	@PostConstruct
	public void init() {
		kompNiveau = new KompNiveau();
	}

	public KompNiveau getKompNiveau() {
		return kompNiveau;
	}

	public void setKompNiveau(KompNiveau kompNiveau) {
		this.kompNiveau = kompNiveau;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

}
