package de.thb.fbi.project.koma.data;

import javax.persistence.Entity;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class Fertigkeit extends NamedEntity {

	private MassId massID;

	public MassId getMassID() {
		return massID;
	}

	public void setMassID(MassId massID) {
		this.massID = massID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fertigkeit [");
		builder.append(super.toString());
		builder.append(", massID=");
		builder.append(massID);
		builder.append("]");
		return builder.toString();
	}

}
