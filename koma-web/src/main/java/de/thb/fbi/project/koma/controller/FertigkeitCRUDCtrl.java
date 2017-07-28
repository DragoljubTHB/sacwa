package de.thb.fbi.project.koma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.display.FertigkeitDisplay;
import de.thb.fbi.project.koma.service.api.FertigkeitService;

@Named
@RequestScoped
public class FertigkeitCRUDCtrl extends GenericCRUDCtrl<Fertigkeit, FertigkeitService> {

	@Inject private Logger logger;
	
	@Inject private FertigkeitService fsi;
	
	private List<Kompetenz> selectedKompetenzen;
	private Fertigkeit fertigkeit;
	
	@PostConstruct
	public void init(){
		fertigkeit = new Fertigkeit();
		selectedKompetenzen = new ArrayList<Kompetenz>();
		logger.info("INIT");
	}
	
	public String doCreate(){
		try{
			logger.info("doCreate ");	
			create(fertigkeit);
			super.addMessage("created", "creation successfull");
		}catch (Exception e) {
			super.addMessage(e.getMessage(), null);
		}
		logger.info("created ");		
		
		return (null);
	}
	public String doFind(int aId){
		logger.info("find : ");
		fertigkeit = readById(aId);
		logger.info("found: {}", fertigkeit.getName());
		logger.info("found: {}", fertigkeit.getMassID());
		return (null);
	}
	
	public void clean(){
		fertigkeit.setName("");
		fertigkeit.setKommentar("");
		this.setSelectedKompetenzen(null);
	}
	
	public void deleteWithKB(FertigkeitDisplay fertigkeitDisplay){
		logger.info("deleteWith KB");
	}
	
	
	@Override
	protected Fertigkeit getEntityType() {
		return fertigkeit;
		
	}

	@Override
	protected FertigkeitService getServiceType() {
		return fsi;
		
	}

	public Fertigkeit getFertigkeit() {
		return fertigkeit;
	}
	public void setFertigkeit(Fertigkeit fertigkeit) {
		this.fertigkeit = fertigkeit;
	}
	

	public List<Kompetenz> getSelectedKompetenzen() {
		return selectedKompetenzen;
	}

	public void setSelectedKompetenzen(List<Kompetenz> selectedKompetenzen) {
		this.selectedKompetenzen = selectedKompetenzen;
	}
	

	
	
}
