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

import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.service.api.KompBereichService;

@Named
@RequestScoped
public class KompBereichListProducer {
	@Inject
	private KompBereichService kbsi;

	private List<KompBereich> kompBereiche = new ArrayList<KompBereich>();

	@PostConstruct
	public void retrieveAllKompBereiche() {
		kompBereiche = kbsi.findAll();
	}

	@Produces
	@Named
	public List<KompBereich> getKompBereiche() {
		return kompBereiche;
	}

	public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final KompBereich kompBereich) {
		retrieveAllKompBereiche();

	}
}
