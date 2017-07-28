package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.display.SchluesselKompDisplay;
import de.thb.fbi.project.koma.util.SchluesselKompListProducer;

@Named
@SessionScoped
public class SchluesselKompListCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<SchluesselKompDisplay> schluesselDisplays;

	@Inject
	private SchluesselKompListProducer schluesselListProducer;
	@Inject
	private SchluesselKompCRUDCtrl crud;

	@PostConstruct
	public void init() {
		schluesselDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<SchluesselKomp> kompFelds = schluesselListProducer.getSchluesselKompetenzen();
		SchluesselKompDisplay kd = null;
		schluesselDisplays.clear();
		for (SchluesselKomp k : kompFelds) {
			kd = new SchluesselKompDisplay();
			kd.setSchluesselKomp(k);
			kd.setEditable(false);
			schluesselDisplays.add(kd);
		}
		return (null);
	}

	public String create(SchluesselKompDisplay kd) {
		crud.create(kd.getSchluesselKomp());
		kd.setEditable(false);
		refreshList();
		return (null);
	}

	public String update(SchluesselKompDisplay kd) {
		instantiateKompetenzFeld(kd);
		crud.update(kd.getSchluesselKomp());
		kd.setEditable(false);
		return (null);
	}

	public void delete(SchluesselKompDisplay kd) {
		crud.delete(kd.getSchluesselKomp());
		kd.setEditable(false);
		refreshList();
	}

	public void deleteWithKB(SchluesselKompDisplay kd) {
		kd.setSchluesselKomp(null);
		crud.update(kd.getSchluesselKomp());
		delete(kd);
	}

	private void instantiateKompetenzFeld(SchluesselKompDisplay kd) {
		kd.setEditable(true);

	}

	public List<SchluesselKompDisplay> getSchluesselDisplays() {
		return schluesselDisplays;
	}

	public void setSchluesselDisplays(List<SchluesselKompDisplay> schluesselDisplays) {
		this.schluesselDisplays = schluesselDisplays;
	}

	public String goBack() {
		return "all_schluesselKomp.xhtml";
	}

	public String goToEdit() {
		return "edit_schluesselKomp.xhtml";
	}
	
	public String goToKompBereich() {
		return "all_kompBereich.xhtml?faces-redirect=true";
	}
}
