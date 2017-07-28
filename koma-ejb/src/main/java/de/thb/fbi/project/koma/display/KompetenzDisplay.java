package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Kompetenz;

public class KompetenzDisplay {

	private boolean editable;
	private Kompetenz kompetenz;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Kompetenz getKompetenz() {
		return kompetenz;
	}

	public void setKompetenz(Kompetenz kompetenz) {
		this.kompetenz = kompetenz;
	}

}
