package de.thb.fbi.project.koma.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.service.api.SchluesselKompService;

@Named
@RequestScoped
public class SchluesselKompListProducer {

	@Inject
	private SchluesselKompService schluesselServ;

	private List<SchluesselKomp> schluesselKompetenzen = new ArrayList<SchluesselKomp>();

	@PostConstruct
	public void retrieveAllSchluesselKomp() {
		schluesselKompetenzen = schluesselServ.findAll();

	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SchluesselKomp schluessel) {
		retrieveAllSchluesselKomp();

	}

	@Produces
	@Named
	public List<SchluesselKomp> getSchluesselKompetenzen() {
		return schluesselKompetenzen;
	}

	public void setSchluesselKompetenzen(List<SchluesselKomp> schluesselKompetenzen) {
		this.schluesselKompetenzen = schluesselKompetenzen;
	}

}
