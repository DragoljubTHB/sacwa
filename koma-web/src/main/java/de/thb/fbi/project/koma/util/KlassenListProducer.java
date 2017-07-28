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

import de.thb.fbi.project.koma.data.Klassenstufe;
import de.thb.fbi.project.koma.service.api.KlassenstufenService;

@RequestScoped
@Named
public class KlassenListProducer {

	@Inject
	private Logger logger;

	@Inject
	private KlassenstufenService klassenService;

	private List<Klassenstufe> klassen = new ArrayList<Klassenstufe>();

	@Produces
	@Named
	public List<Klassenstufe> getKlassen() {
		logger.info("-> @produces");
		return klassen;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Klassenstufe klasse) {
		logger.info("-> produces :: onListChanged");

		retrieveAllKlassen();
	}

	@PostConstruct
	public void retrieveAllKlassen() {
		logger.info("-> produces::retrieveAllKlassen ");

		klassen = klassenService.findAll();

		for (Klassenstufe e : klassen) {
			logger.info("<- result list {}", e.getName());
		}
	}
}
