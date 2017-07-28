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

import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

@RequestScoped
@Named
public class KompNiveauListProducer {

	@Inject
	private Logger logger;

	@Inject
	private KompNiveauService knsi;

	private List<KompNiveau> kompNiveaus = new ArrayList<KompNiveau>();

	@PostConstruct
	public void retrieveAllKompNiveaus() {
		logger.info("-> produces::retrieveAllKompNiveaus ");

		kompNiveaus = knsi.findAll();

		for (KompNiveau k : kompNiveaus) {
			logger.info("<- result list {}", k.getName());

		}
	}

	@Produces
	@Named
	public List<KompNiveau> getKompNiveaus() {
		logger.info("-> @produces");
		return kompNiveaus;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final KompNiveau kompNiveau) {
		logger.info("-> produces :: onListChanged");
		retrieveAllKompNiveaus();

	}
}
