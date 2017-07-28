package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Sequenz;

public class SequenzDisplay {
	private Sequenz sequenz;

	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Sequenz getSequenz() {
		return sequenz;
	}

	public void setSequenz(Sequenz sequenz) {
		this.sequenz = sequenz;
	}

}
