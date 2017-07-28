package de.thb.fbi.project.koma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.service.api.SchluesselKompService;

@Named
@RequestScoped
public class SchluesselKompCRUDCtrl extends GenericCRUDCtrl<SchluesselKomp, SchluesselKompService> {

	@Inject
	private Logger logger;

	private SchluesselKomp schluesselKomp;

	private List<KompBereich> selectedKompBereiche;

	@Inject
	private SchluesselKompService sksi;

	@PostConstruct
	public void init() {
		schluesselKomp = new SchluesselKomp();
		selectedKompBereiche = new ArrayList<KompBereich>();
		logger.info("INIT");
		// fillDB();

	}

	@Override
	public String create() {
		try {
			logger.info("-> create {}", schluesselKomp);
			// sksi.create(name, kommentar, selectedKompBereiche);
			create(schluesselKomp);
			if (!selectedKompBereiche.isEmpty()) {
				schluesselKomp.setKompBereiche(selectedKompBereiche);
				update(schluesselKomp);
			}
			super.addMessage("created", "creation successfull");
			clean();
		} catch (Exception e) {
			super.addMessage(e.getMessage(), null);
			logger.warn("createSchluesselKomp Exception", e);
		}

		return null;
	}

	public void clean() {
		schluesselKomp.setName("");
		schluesselKomp.setKommentar("");
		this.setSelectedKompBereiche(null);
	}

	public List<KompBereich> getSelectedKompBereiche() {
		return selectedKompBereiche;
	}

	public void setSelectedKompBereiche(List<KompBereich> selectedKompBereiche) {
		this.selectedKompBereiche = selectedKompBereiche;
	}

	@Override
	protected SchluesselKomp getEntityType() {
		return new SchluesselKomp();

	}

	@Override
	protected SchluesselKompService getServiceType() {
		return sksi;

	}

	public SchluesselKomp getSchluesselKomp() {
		return schluesselKomp;
	}

	public void setSchluesselKomp(SchluesselKomp schluesselKomp) {
		this.schluesselKomp = schluesselKomp;
	}
}
