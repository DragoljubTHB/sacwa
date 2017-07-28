package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Stunde;

public class StundeDisplay {
	private Stunde stunde;

	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Stunde getStunde() {
		return stunde;
	}

	public void setStunde(Stunde stunde) {
		this.stunde = stunde;
	}
}
