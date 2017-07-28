package de.thb.fbi.project.koma.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.data.SchluesselKomp;
import de.thb.fbi.project.koma.service.api.KompBereichService;

@Named
@RequestScoped
public class KompBereichCtrl extends GenericCRUDCtrl<KompBereich, KompBereichService> {

	@Inject
	private Logger logger;

	private KompBereich kompBereich = new KompBereich();
	@Inject
	private KompBereichService kbsi;
	
	private List<Kompetenz> selectedKompetenzen;

	@Override
	protected KompBereich getEntityType() {
		return kompBereich;
	}

	@Override
	protected KompBereichService getServiceType() {
		return kbsi;
	}

	/*
	 * test query
	 * 
	 */
	public String doFindSchluesselKompetenz(int aId) {
		logger.info("Attempt to super.readById {}", aId);
		KompBereich k = new KompBereich();
		k = super.readById(aId);
		List<Kompetenz> kompetenzen = kbsi.findKompetenzenByBereich(aId);
		logger.info("Read {}", k.getName());
		if (k.getKompetenzen() != null) {
			for (Kompetenz kompetenz : kompetenzen) {
				logger.info("Kompetenz {}", kompetenz.getName());
			}
		}
		if (k.getSchluesselKopetenzen() != null) {
			for (SchluesselKomp s : k.getSchluesselKopetenzen()) {
				logger.info("SchluesselKompetenz {}", s.getName());
			}
		}

		return (null);
	}

	@Override
	public String create() {
		kbsi.create(kompBereich);
		return (null);
	}

	public String create(KompBereich kompBereich) {
		kbsi.create(kompBereich);
		return (null);
	}

	public void clean() {
		kompBereich.setName("");
		kompBereich.setKommentar("");
//		this.setSelectedKompBereiche(null);
	}
	/*
	 * std
	 */
	public KompBereich getKompBereich() {
		return kompBereich;
	}

	public void setKompBereich(KompBereich kompBereich) {
		this.kompBereich = kompBereich;
	}

	public List<Kompetenz> getSelectedKompetenzen() {
		return selectedKompetenzen;
	}

	public void setSelectedKompetenzen(List<Kompetenz> selectedKompetenzen) {
		this.selectedKompetenzen = selectedKompetenzen;
	}


}
