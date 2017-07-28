package de.thb.fbi.project.koma.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Kompetenz extends NamedEntity {

	@ManyToOne(cascade = { CascadeType.ALL })
	private KompBereich kompBereich;

	public KompBereich getKompBereich() {
		return kompBereich;
	}

	public void setKompBereich(KompBereich kompBereich) {
		this.kompBereich = kompBereich;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kompetenz [");
		builder.append(super.toString());
		builder.append(", kompBereich=");
		builder.append(kompBereich);
		builder.append("]");
		return builder.toString();
	}

}
