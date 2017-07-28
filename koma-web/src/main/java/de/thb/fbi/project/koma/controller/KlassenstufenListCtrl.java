package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.Klassenstufe;
import de.thb.fbi.project.koma.display.KlasseDisplay;
import de.thb.fbi.project.koma.util.KlassenListProducer;

@Named
@SessionScoped

public class KlassenstufenListCtrl implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<KlasseDisplay> klasseDisplays;

	@Inject
	private KlassenListProducer klassenListProducer;
	@Inject
	private KlassenstufenCtrl crud;

	@PostConstruct
	public void init() {
		klasseDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<Klassenstufe> klassen = klassenListProducer.getKlassen();
		KlasseDisplay kd = null;
		klasseDisplays.clear();
		for (Klassenstufe k : klassen) {
			kd = new KlasseDisplay();
			kd.setKlasse(k);
			kd.setEditable(false);
			klasseDisplays.add(kd);
		}
		return (null);
	}

	public String editAction(KlasseDisplay kd) {
		instantiateKlassenstufen(kd);
		// kd.setEditable(true);
		return (null);
	}

	public String update(KlasseDisplay kd) {
		crud.update(kd.getKlasse());
		kd.setEditable(false);
		return (null);
	}

	public String delete(KlasseDisplay kd) {
		crud.delete(kd.getKlasse());
		kd.setEditable(false);
		refreshList();
		return (null);
	}

	/*
	 * hilfe methode fuer editAction
	 */
	private void instantiateKlassenstufen(KlasseDisplay kd) {
		kd.setEditable(true);

	}

	public List<KlasseDisplay> getKlasseDisplays() {
		return klasseDisplays;
	}

	public void setKlasseDisplays(List<KlasseDisplay> fachDisplays) {
		this.klasseDisplays = fachDisplays;
	}

}
