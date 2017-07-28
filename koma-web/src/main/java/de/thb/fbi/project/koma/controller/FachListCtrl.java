package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.display.FachDisplay;
import de.thb.fbi.project.koma.util.FachListProducer;

@Named
@SessionScoped
public class FachListCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FachDisplay> fachDisplays;

	@Inject
	private FachListProducer faecherListProducer;
	@Inject
	private FachCtrl crud;

	@PostConstruct
	public void init() {
		fachDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<Fach> faecher = faecherListProducer.getFaecher();
		FachDisplay kd = null;
		fachDisplays.clear();
		for (Fach k : faecher) {
			kd = new FachDisplay();
			kd.setFach(k);
			kd.setEditable(false);
			fachDisplays.add(kd);
		}
		return (null);
	}

	public String editAction(FachDisplay kd) {
		instantiateFach(kd);
		// kd.setEditable(true);
		return (null);
	}

	public String update(FachDisplay kd) {
		crud.update(kd.getFach());
		kd.setEditable(false);
		return (null);
	}

	public String delete(FachDisplay kd) {
		crud.delete(kd.getFach());
		kd.setEditable(false);	
		refreshList();
		return (null);
	}

	/*
	 * hilfe methode fuer editAction
	 */
	private void instantiateFach(FachDisplay kd) {
		kd.setEditable(true);

	}

	public List<FachDisplay> getFachDisplays() {
		return fachDisplays;
	}

	public void setFachDisplays(List<FachDisplay> fachDisplays) {
		this.fachDisplays = fachDisplays;
	}

}
