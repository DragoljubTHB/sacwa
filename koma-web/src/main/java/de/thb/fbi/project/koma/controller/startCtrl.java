package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class startCtrl {

	public String createSchluesselKomp() {
		return "all_schluesselKomp.xhtml?faces-redirect=true";
	}

	public String createKompBereich() {
		return "all_kompBereich.xhtml?faces-redirect=true";
	}

	public String createKomp() {
		return "kompetenz_list2.xhtml?faces-redirect=true";
	}

	public String createKompNiveau() {
		return "new_kompetenzniveau.xhtml?faces-redirect=true";
	}

	public String createKlasse() {
		return "new_klasse.xhtml?faces-redirect=true";
	}

	public String createFach() {
		return "new_fach.xhtml?faces-redirect=true";
	}

	public String createFertigkeit() {
		return "new_fertigkeit.xhtml?faces-redirect=true";
	}

	public String createSequenz() {
		return "new_sequenz.xhtml?faces-redirect=true";
	}

	public String createStunde() {
		return "new_stunde.xhtml?faces-redirect=true";
	}
}
