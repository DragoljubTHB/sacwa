package de.thb.data;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class MassId implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private KompNiveau kompNiveau;

	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private Kompetenz kompetenz;

	public KompNiveau getKompNiveau() {
		return kompNiveau;
	}

	public void setKompNiveau(KompNiveau kompNiveau) {
		this.kompNiveau = kompNiveau;
	}

	public Kompetenz getKompetenz() {
		return kompetenz;
	}

	public void setKompetenz(Kompetenz kompetenz) {
		this.kompetenz = kompetenz;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MassId [");
		builder.append(super.toString());
		builder.append(", kompNiveau=");
		builder.append(kompNiveau);
		builder.append(", kompetenz=");
		builder.append(kompetenz);
		builder.append("]");
		return builder.toString();
	}

}
