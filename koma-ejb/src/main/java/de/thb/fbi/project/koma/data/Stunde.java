package de.thb.fbi.project.koma.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Stunde extends NamedEntity {
	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private Sequenz sequenz;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Fertigkeit> neueFertigkeiten;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Fertigkeit> zufestigendeFertigkeiten;

	private String inhalt;
	private int ordnung;

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public Sequenz getSequenz() {
		return sequenz;
	}

	public void setSequenz(Sequenz sequenz) {
		this.sequenz = sequenz;
	}

	public List<Fertigkeit> getNeueFertigkeiten() {
		return neueFertigkeiten;
	}

	public void setNeueFertigkeiten(List<Fertigkeit> neueFertigkeiten) {
		this.neueFertigkeiten = neueFertigkeiten;
	}

	public List<Fertigkeit> getZufestigendeFertigkeiten() {
		return zufestigendeFertigkeiten;
	}

	public void setZufestigendeFertigkeiten(List<Fertigkeit> zufestigendeFertigkeiten) {
		this.zufestigendeFertigkeiten = zufestigendeFertigkeiten;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stunde [");
		builder.append(super.toString());
		builder.append(", sequenz=");
		builder.append(sequenz);
		builder.append(", neueFertigkeiten=");
		builder.append(neueFertigkeiten);
		builder.append(", zufestigendeFertigkeiten=");
		builder.append(zufestigendeFertigkeiten);
		builder.append(", inhalt=");
		builder.append(inhalt);
		builder.append("]");
		return builder.toString();
	}

	public int getOrdnung() {
		return ordnung;
	}

	public void setOrdnung(int ordnung) {
		this.ordnung = ordnung;
	}

}
