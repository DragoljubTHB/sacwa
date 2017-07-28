package de.thb.fbi.project.koma.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class KlasseFachId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private Klassenstufe klasse;

	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private Fach fach;

	public Klassenstufe getKlasse() {
		return klasse;
	}

	public void setKlasse(Klassenstufe klasse) {
		this.klasse = klasse;
	}

	public Fach getFach() {
		return fach;
	}

	public void setFach(Fach fach) {
		this.fach = fach;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KlasseFachId [");
		builder.append(super.toString());
		builder.append(", klassen=");
		builder.append(klasse);
		builder.append(", fach=");
		builder.append(fach);
		builder.append("]");
		return builder.toString();
	}

}
