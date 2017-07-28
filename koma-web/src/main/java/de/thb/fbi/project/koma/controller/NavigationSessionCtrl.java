package de.thb.fbi.project.koma.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.data.SchluesselKomp;

@Named
@SessionScoped
public class NavigationSessionCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private SchluesselKomp lastSelectedSchluesselKomp;
	private KompBereich lastSelectedKompBereich;
	private Kompetenz lastSelectedKompetenz;
	private Fertigkeit lastSelectedFertigkeit;

	public SchluesselKomp getLastSelectedSchluesselKomp() {
		return lastSelectedSchluesselKomp;
	}

	public void setLastSelectedSchluesselKomp(SchluesselKomp lastSelectedSchluesselKomp) {
		this.lastSelectedSchluesselKomp = lastSelectedSchluesselKomp;
	}

	public KompBereich getLastSelectedKompBereich() {
		return lastSelectedKompBereich;
	}

	public void setLastSelectedKompBereich(KompBereich lastSelectedKompBereich) {
		this.lastSelectedKompBereich = lastSelectedKompBereich;
	}

	public Kompetenz getLastSelectedKompetenz() {
		return lastSelectedKompetenz;
	}

	public void setLastSelectedKompetenz(Kompetenz lastSelectedKompetenz) {
		this.lastSelectedKompetenz = lastSelectedKompetenz;
	}

	public Fertigkeit getLastSelectedFertigkeit() {
		return lastSelectedFertigkeit;
	}

	public void setLastSelectedFertigkeit(Fertigkeit lastSelectedFertigkeit) {
		this.lastSelectedFertigkeit = lastSelectedFertigkeit;
	}

}
