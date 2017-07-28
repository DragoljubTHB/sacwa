package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.display.KompetenzDisplay;
import de.thb.fbi.project.koma.util.KompetenzProducer;

@Named
@SessionScoped
public class KompetenzListCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<KompetenzDisplay> kompetenzDisplays;

	@Inject
	private KompetenzProducer kompetenzProducer;
	// private ListUtils utils;
	@Inject
	private KompetenzCRUDCtrl crud;

	@PostConstruct
	public void init() {
		kompetenzDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<Kompetenz> kompetenzs = kompetenzProducer.getKompetenzen();
		KompetenzDisplay kd = null;
		kompetenzDisplays.clear();
		for (Kompetenz k : kompetenzs) {
			kd = new KompetenzDisplay();
			kd.setKompetenz(k);
			kd.setEditable(false);
			kompetenzDisplays.add(kd);
		}
		return (null);
	}

	public String editAction(KompetenzDisplay kd) {
		instantiateKompetenz(kd);
		// kd.setEditable(true);
		return (null);
	}

	public String create(KompetenzDisplay kd) {
		crud.create(kd.getKompetenz());
		kd.setEditable(false);
		return (null);
	}

	public String update(KompetenzDisplay kd) {
		crud.update(kd.getKompetenz());
		kd.setEditable(false);
		return (null);
	}

	public String delete(KompetenzDisplay kd) {
		crud.delete(kd.getKompetenz());
		kd.setEditable(false);
		refreshList(); // so dass im UI sich die List ohne diesem element
						// anzeigt
		return (null);
	}

	/*
	 * hilfe methode fuer editAction
	 */
	private void instantiateKompetenz(KompetenzDisplay kd) {
		Kompetenz k = kd.getKompetenz();
		kd.setEditable(true);
		k.setKompBereich(new KompBereich());

	}

	/*
	 * std
	 */
	public List<KompetenzDisplay> getKompetenzDisplays() {
		return kompetenzDisplays;
	}

	public void setKompetenzDisplays(List<KompetenzDisplay> kompetenzDisplays) {
		this.kompetenzDisplays = kompetenzDisplays;
	}

}
