package de.thb.fbi.project.koma.data;

import javax.persistence.Entity;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Klassenstufe extends NamedEntity {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Klassenstufe [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
