package de.thb.fbi.project.koma.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.data.Kompetenz;
import de.thb.fbi.project.koma.data.MassId;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

@Named
@RequestScoped
public class MassCRUDCtrl {
	@Inject
	private Logger logger;

	@Inject
	private KompetenzCRUDCtrl kCRUD;
	@Inject
	private KompNiveauService knCRUD;
	@Inject
	private FertigkeitCRUDCtrl fCRUD;

	private Kompetenz k;
	private KompNiveau kn;
	private Fertigkeit f;
	private MassId massId;

	@PostConstruct
	public void init() {
		k = new Kompetenz();
		kn = new KompNiveau();
		f = new Fertigkeit();
		massId = new MassId();
	}

	public String doCreate(String aName, int rang) {
		k.setName(aName);
		kn.setName(aName);
		kn.setRang(rang);
		f.setName(aName);

		kCRUD.create(k);
		knCRUD.create(kn);
		fCRUD.create(f);

		logger.info("attempt to getMassId ");
		massId.setKompNiveau(kn);
		massId.setKompetenz(k);
		logger.info("setMassId {} in fertigkeit {}", massId.getClass(), f.getName());
		f.setMassID(massId);
		fCRUD.update(f);
		logger.info("fertigkeit id {}", f.getId());

		return (null);
	}
	public String doFind(int id){
		f = fCRUD.readById(id);
		logger.info("found: {}", f.toString());
		
		return (null);
	}
	
	
	/*
	 * std
	 */

	public Kompetenz getK() {
		return k;
	}

	public void setK(Kompetenz k) {
		this.k = k;
	}

	public KompNiveau getKn() {
		return kn;
	}

	public void setKn(KompNiveau kn) {
		this.kn = kn;
	}

	public Fertigkeit getF() {
		return f;
	}

	public void setF(Fertigkeit f) {
		this.f = f;
	}

	public MassId getMassId() {
		return massId;
	}

	public void setMassId(MassId massId) {
		this.massId = massId;
	}

}
