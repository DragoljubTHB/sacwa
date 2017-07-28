package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.display.FertigkeitDisplay;
import de.thb.fbi.project.koma.util.FertigkeitListProducer;

@Named
@SessionScoped
public class FertigkeitListCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject FertigkeitListProducer fertigkeitListProducer;

	
	private List<FertigkeitDisplay> fertigkeitDisplayList;
	private KompNiveau kompNiveau;
	private Kompetenz kompetenz;
	
	
	@PostConstruct
	public void init(){
		fertigkeitDisplayList = new ArrayList<>();
		setKompNiveau(new KompNiveau());
		setKompetenz(new Kompetenz());
		
		refreshList();
	}

	
	public String refreshList() {
		List<Fertigkeit> fertigkeiten = fertigkeitListProducer.getFertigkeite();
		FertigkeitDisplay fd = null;
		fertigkeitDisplayList.clear();
		for(Fertigkeit f : fertigkeiten){
			fd = new FertigkeitDisplay();
			fd.setFertigkeit(f);
			fd.setEditable(false);
			fertigkeitDisplayList.add(fd);
		}
		return (null);
	}
	/*
	 * 
	 */

	public List<FertigkeitDisplay> getFertigkeitDisplayList() {
		return fertigkeitDisplayList;
	}

	public void setFertigkeitDisplayList(List<FertigkeitDisplay> fertigkeitDisplayList) {
		this.fertigkeitDisplayList = fertigkeitDisplayList;
	}


	public KompNiveau getKompNiveau() {
		return kompNiveau;
	}


	public void setKompNiveau(KompNiveau kompNiveau) {
		this.kompNiveau = kompNiveau;
	}


	public Kompetenz getKompetenz() {
		return kompetenz;
	}


	public void setKompetenz(Kompetenz kompetenz) {
		this.kompetenz = kompetenz;
	}

}
