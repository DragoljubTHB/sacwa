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

import org.slf4j.Logger;

import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.service.api.KompetenzService;

@RequestScoped
@Named
public class KompetenzProducer {

	@Inject
	private Logger logger;

	@Inject
	private KompetenzService ksi;
	@Inject
	ListUtils utils;

	private List<Kompetenz> kompetenzen = new ArrayList<Kompetenz>();
	private List<String> kompetenzMethods;

	@PostConstruct
	public void retrieveAllKompetenzen() {
		logger.info("-> produces::retrieveAllKompNiveaus ");

		kompetenzen = ksi.findAll();
		if (kompetenzen.isEmpty()) {
			logger.info("-> produces:: istEmpty ");
			kompetenzen.add(new Kompetenz());
		}

		for (Kompetenz k : kompetenzen) {
			logger.info("<- result list {}", k.getName());

		}
		kompetenzMethods = utils.resolveEntityMethods(kompetenzen.get(0));
	}

	@Produces
	@Named
	public List<Kompetenz> getKompetenzen() {
		logger.info("-> @produces");
		return kompetenzen;
	}

	@Named
	public List<String> getKompetenzMethods() {
		return kompetenzMethods;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Kompetenz kompetenz) {
		logger.info("-> produces :: onListChanged");
		retrieveAllKompetenzen();

	}
}
