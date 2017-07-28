package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Fach;

public class FachDisplay {
	private Fach fach;

	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Fach getFach() {
		return fach;
	}

	public void setFach(Fach fach) {
		this.fach = fach;
	}
}
