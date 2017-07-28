package de.thb.fbi.project.koma.data;

import javax.persistence.Entity;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Fach extends NamedEntity {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fach [");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
