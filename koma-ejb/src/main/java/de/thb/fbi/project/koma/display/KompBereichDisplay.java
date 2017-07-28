package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.KompBereich;

public class KompBereichDisplay {
	public boolean editable;
	public KompBereich kompBereich;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public KompBereich getKompBereich() {
		return kompBereich;
	}

	public void setKompBereich(KompBereich kompBereich) {
		this.kompBereich = kompBereich;
	}

}
