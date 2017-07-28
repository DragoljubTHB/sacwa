package de.thb.fbi.project.koma.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.Stunde;
import de.thb.fbi.project.koma.service.api.StundeService;

//@ManagedBean
@Named
@RequestScoped
public class StundeCtrl extends GenericCRUDCtrl<Stunde, StundeService> {

	private List<Stunde> stundenList;

	private Stunde stunde;

	private final String newStunde = "new_stunde.xhtml";

	@Inject
	private StundeService myService;

	@PostConstruct
	public void init() {
		stunde = new Stunde();
		stundenList = myService.findAll();
		if (stundenList == null)
			stundenList = new ArrayList<Stunde>();
	}

	public String create() {
		myService.create(stunde);
		addFacesMessage(FacesMessage.SEVERITY_INFO, "New message has been added successfully");
		return newStunde;
	}

	public void findAllStunde() {
		this.stundenList = myService.findAll();
	}

	public String delete() {

		FacesContext fc = FacesContext.getCurrentInstance();
		if (stundenList == null || stunde == null) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler: Datenelement fehlt.", null));
			return null;
		}
		fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Einzlethema wurde gelöscht", null));

		myService.delete(stunde);
		return newStunde;
	}

	private void addFacesMessage(Severity severityError, String msg) {
		FacesMessage facesMsg = new FacesMessage(severityError, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);

	}

	public List<Stunde> getStundenList() {
		return stundenList;
	}

	public void setStundenList(List<Stunde> stundenList) {
		this.stundenList = stundenList;
	}

	public Stunde getStunde() {
		return stunde;
	}

	public void setStunde(Stunde stunde) {
		this.stunde = stunde;
	}

	@Override
	protected Stunde getEntityType() {
		return stunde;
	}

	@Override
	protected StundeService getServiceType() {
		return myService;
	}

}
