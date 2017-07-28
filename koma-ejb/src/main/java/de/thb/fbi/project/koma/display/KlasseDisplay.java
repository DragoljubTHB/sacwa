package de.thb.fbi.project.koma.display;

import de.thb.fbi.project.koma.data.Klassenstufe;

public class KlasseDisplay {
	private Klassenstufe klasse;
	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Klassenstufe getKlasse() {
		return klasse;
	}

	public void setKlasse(Klassenstufe klasse) {
		this.klasse = klasse;
	}

}
