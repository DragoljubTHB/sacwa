package de.thb.fbi.project.koma.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Sequenz extends NamedEntity {

	private KlasseFachId klfaID;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "sequenz")
	@OrderBy("odnung")
	private List<Stunde> stunden;

	private int ordnung;

	public int getOrdnung() {
		return ordnung;
	}

	public void setOrdnung(int ordnung) {
		this.ordnung = ordnung;
	}

	public List<Stunde> getStunden() {
		return stunden;
	}

	public void setStunden(List<Stunde> stunden) {
		this.stunden = stunden;
	}

	public KlasseFachId getKlfaID() {
		return klfaID;
	}

	public void setKlfaID(KlasseFachId klfaID) {
		this.klfaID = klfaID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sequenz [");
		builder.append(super.toString());
		builder.append(", fach=");
		builder.append(klfaID.getFach());
		builder.append(", klassen=");
		builder.append(klfaID.getKlasse());
		builder.append(", stunden=");
		builder.append(stunden);
		builder.append("]");
		return builder.toString();
	}

}
