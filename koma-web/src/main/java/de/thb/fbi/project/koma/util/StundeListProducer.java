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

import de.thb.fbi.project.koma.data.Stunde;
import de.thb.fbi.project.koma.service.api.StundeService;

@RequestScoped
@Named
public class StundeListProducer {

	@Inject
	private Logger logger;

	@Inject
	private StundeService myService;

	private List<Stunde> stunden = new ArrayList<Stunde>();

	@PostConstruct
	public void retrieveStunden() {
		logger.info("-> produces::retrieveAllEinzelthemen ");

		stunden = myService.findAll();

		for (Stunde e : stunden) {
			logger.info("<- result list {}", e.getName());

		}
	}

	@Produces
	@Named
	public List<Stunde> getStunden() {
		logger.info("-> @produces");
		return stunden;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Stunde stunde) {
		logger.info("-> produces :: onListChanged");
		retrieveStunden();

	}
}
