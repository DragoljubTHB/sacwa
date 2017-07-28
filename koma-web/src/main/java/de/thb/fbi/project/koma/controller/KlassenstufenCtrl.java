package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.Klassenstufe;
import de.thb.fbi.project.koma.service.api.KlassenstufenService;

@Named
@RequestScoped
public class KlassenstufenCtrl extends GenericCRUDCtrl<Klassenstufe, KlassenstufenService> {
	@Inject
	private KlassenstufenService myService;

	private Klassenstufe klasse = new Klassenstufe();

	public String create() {
		super.create(klasse);
		klasse.setName("");
		klasse.setKommentar("");
		addMessage("Neue Klassenstufe angelegt");

		return null;
	}

	public String delete() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (klasse == null) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler: Datenelement fehlt.", null));
			return null;
		}
		addMessage("Einzlethema wurde gelöscht");

		myService.delete(klasse);
		return null;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Klassenstufe getKlasse() {
		return klasse;
	}

	public void setKlasse(Klassenstufe klasse) {
		this.klasse = klasse;
	}

	@Override
	protected Klassenstufe getEntityType() {
		return klasse;
	}

	@Override
	protected KlassenstufenService getServiceType() {
		return myService;
	}

}
