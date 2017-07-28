package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.SchluesselKomp;

public class SchluesselKompDisplay {

	private SchluesselKomp schluesselKomp;

	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public SchluesselKomp getSchluesselKomp() {
		return schluesselKomp;
	}

	public void setSchluesselKomp(SchluesselKomp schluesselKomp) {
		this.schluesselKomp = schluesselKomp;
	}

}
