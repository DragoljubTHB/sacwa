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

import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.service.api.FachService;

@RequestScoped
@Named
public class FachListProducer {

	@Inject
	private Logger logger;

	@Inject
	private FachService myService;

	private List<Fach> faecher = new ArrayList<Fach>();

	@Produces
	@Named
	public List<Fach> getFaecher() {
		logger.info("-> @produces");
		return faecher;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Fach fach) {
		logger.info("-> produces :: onListChanged");

		retrieveAllFaecher();
	}

	@PostConstruct
	public void retrieveAllFaecher() {
		logger.info("-> produces::retrieveAllFaecher ");

		faecher = myService.findAll();

		for (Fach e : faecher) {
			logger.info("<- result list {}", e.getName());
		}
	}
}
