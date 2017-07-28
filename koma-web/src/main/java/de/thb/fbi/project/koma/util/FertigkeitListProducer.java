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

import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.service.api.FertigkeitService;

@Named
@RequestScoped
public class FertigkeitListProducer {
	@Inject
	private FertigkeitService fertigkeitServ;

	private List<Fertigkeit> fertigkeiten = new ArrayList<Fertigkeit>();

	@PostConstruct
	public void retrieveAllFertigkeiten() {
		fertigkeiten = fertigkeitServ.findAll();
	}

	@Produces
	@Named
	public List<Fertigkeit> getFertigkeite() {
		return fertigkeiten;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Fertigkeit fertigkeit) {
		retrieveAllFertigkeiten();

	}
}
