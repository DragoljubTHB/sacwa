package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Fertigkeit;

public class FertigkeitDisplay {
	private Fertigkeit fertigkeit;
	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Fertigkeit getFertigkeit() {
		return fertigkeit;
	}

	public void setFertigkeit(Fertigkeit fertigkeit) {
		this.fertigkeit = fertigkeit;
	}

}
