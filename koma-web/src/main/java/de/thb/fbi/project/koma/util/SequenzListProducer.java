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

import de.thb.fbi.project.koma.data.Sequenz;
import de.thb.fbi.project.koma.service.api.SequenzService;

@RequestScoped
@Named
public class SequenzListProducer {

	@Inject
	private Logger logger;

	@Inject
	private SequenzService myService;

	private List<Sequenz> sequenzen = new ArrayList<Sequenz>();

	@PostConstruct
	public void retrieveSequenz() {
		logger.info("-> produces::retrieveAllSequenzen ");

		sequenzen = myService.findAll();

		for (Sequenz e : sequenzen) {
			logger.info("<- result list {}", e.getName());

		}
	}

	@Produces
	@Named
	public List<Sequenz> getSequenzen() {
		logger.info("-> @produces");
		return sequenzen;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Sequenz sequenz) {
		logger.info("-> produces :: onListChanged");
		retrieveSequenz();

	}
}
