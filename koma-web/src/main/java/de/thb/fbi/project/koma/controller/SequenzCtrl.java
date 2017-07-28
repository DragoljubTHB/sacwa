package de.thb.fbi.project.koma.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.thb.fbi.project.generic.controller.impl.GenericCRUDCtrl;
import de.thb.fbi.project.koma.data.Sequenz;
import de.thb.fbi.project.koma.service.api.SequenzService;

@Named
@RequestScoped
public class SequenzCtrl extends GenericCRUDCtrl<Sequenz, SequenzService> {

	private Sequenz sequenz;
	
	@Inject
	private SequenzService sequenzServ;

	@Override
	protected Sequenz getEntityType() {
		return sequenz;
	}

	@Override
	protected SequenzService getServiceType() {
		return sequenzServ;
	}

	@Override
	public String create() {
		sequenzServ.create(sequenz);
		return (null);
	}

	public String create(Sequenz sequenz) {
		sequenzServ.create(sequenz);
		return (null);
	}

	public Sequenz getSequenz() {
		return sequenz;
	}

	public void setSequenz(Sequenz sequenz) {
		this.sequenz = sequenz;
	}

}
