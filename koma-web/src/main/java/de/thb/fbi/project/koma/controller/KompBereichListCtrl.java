package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.display.KompBereichDisplay;
import de.thb.fbi.project.koma.util.KompBereichListProducer;


@Named
@SessionScoped
public class KompBereichListCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<KompBereichDisplay> kompetenzBereichDisplays;

	@Inject
	private KompBereichListProducer kbProducer;
	@Inject
	private KompBereichCtrl crud;
	
	@Inject
	private NavigationSessionCtrl navi;
	
	private boolean showAll;

	@PostConstruct
	public void init() {
		kompetenzBereichDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<KompBereich> kompBereich = kbProducer.getKompBereiche();
		KompBereichDisplay kd = null;
		kompetenzBereichDisplays.clear();
		for (KompBereich k : kompBereich) {
			kd = new KompBereichDisplay();
			kd.setKompBereich(k);
			kd.setEditable(false);
			kompetenzBereichDisplays.add(kd);
		}
		return (null);
	}

	public String editAction(KompBereichDisplay kd) {
		instantiateKompetenzBereich(kd);
		// kd.setEditable(true);
		return (null);
	}

	public String create(KompBereichDisplay kd) {
		crud.create(kd.getKompBereich());
		kd.setEditable(false);
		refreshList();
		return (null);
	}

	public String update(KompBereichDisplay kd) {
		crud.update(kd.getKompBereich());
		kd.setEditable(false);
		return (null);
	}

	public String delete(KompBereichDisplay kd) {
		crud.delete(kd.getKompBereich());
		kd.setEditable(false);
		refreshList(); // so dass im UI sich die List ohne diesem element
						// anzeigt
		return (null);
	}

	/*
	 * hilfe methode fuer editAction
	 */
	private void instantiateKompetenzBereich(KompBereichDisplay kd) {
		// KompBereich k = kd.getKompBereich();
		// many Kompetenz to kompBereich for each kf: new k & set attrs
		kd.setEditable(true);

	}

	/*
	 * std
	 */
	public List<KompBereichDisplay> getKompetenzBereichDisplays() {
		return kompetenzBereichDisplays;
	}

	public void setKompetenzBereichDisplays(List<KompBereichDisplay> kompetenzBereichDisplays) {
		this.kompetenzBereichDisplays = kompetenzBereichDisplays;
	}
	
	
	public String goToEdit() {
		return "edit_kompBereich.xhtml";
	}
	
	public String goToKompetenz() {
		return "all_Kompetenz.xhtml";
	}

	public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
}
