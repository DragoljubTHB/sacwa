package de.thb.fbi.project.koma.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.Sequenz;
import de.thb.fbi.project.koma.display.SequenzDisplay;
import de.thb.fbi.project.koma.util.SequenzListProducer;

@Named
@SessionScoped
public class SequenzListCtrl implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<SequenzDisplay> sequenzDisplays;

	@Inject
	private SequenzListProducer sequenzListProducer;
	@Inject
	private SequenzCtrl crud;

	@PostConstruct
	public void init() {
		sequenzDisplays = new ArrayList<>();
		refreshList();

	}

	public String refreshList() {
		List<Sequenz> kompFelds = sequenzListProducer.getSequenzen();
		SequenzDisplay kd = null;
		sequenzDisplays.clear();
		for (Sequenz k : kompFelds) {
			kd = new SequenzDisplay();
			kd.setSequenz(k);
			kd.setEditable(false);
			sequenzDisplays.add(kd);
		}
		return (null);
	}

	public String editAction(SequenzDisplay kd) {
		instantiateKompetenzFeld(kd);
		return (null);
	}

	public String create(SequenzDisplay kd) {
		crud.create(kd.getSequenz());
		kd.setEditable(false);
		refreshList();
		return (null);
	}

	public String update(SequenzDisplay kd) {
		crud.update(kd.getSequenz());
		kd.setEditable(false);
		return (null);
	}

	public String delete(SequenzDisplay kd) {
		crud.delete(kd.getSequenz());
		kd.setEditable(false);
		refreshList();
		return (null);
	}

	private void instantiateKompetenzFeld(SequenzDisplay kd) {
		kd.setEditable(true);
	}

	public List<SequenzDisplay> getSequenzDisplays() {
		return sequenzDisplays;
	}

	public void setSequenzDisplays(List<SequenzDisplay> sequenzDisplays) {
		this.sequenzDisplays = sequenzDisplays;
	}

}
