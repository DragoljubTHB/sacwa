package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.Fach;
import de.thb.fbi.project.koma.service.api.FachService;

@RequestScoped
@Named
public class FachCtrl extends GenericCRUDCtrl<Fach, FachService> {
	@Inject
	private FachService fachService;

	private Fach fach;

	@Override
	protected Fach getEntityType() {
		return fach;
	}

	@Override
	protected FachService getServiceType() {
		return fachService;
	}

	@Override
	public String create() {
		fachService.create(fach);
		addMessage("Neues Fach angelegt");
		fach.setName("");
		return null;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String delete() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (fach == null) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler: Datenelement fehlt.", null));
			return null;
		}
		addMessage("Fach wurde gelöscht");

		fachService.delete(fach);
		return null;
	}

	public Fach getFach() {
		return fach;
	}

	public void setFach(Fach fach) {
		this.fach = fach;
	}

}
