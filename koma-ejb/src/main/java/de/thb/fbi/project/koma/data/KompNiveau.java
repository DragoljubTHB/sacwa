package de.thb.fbi.project.koma.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class KompNiveau extends NamedEntity {

	@Column(unique = true)
	@Min(1)
	@Max(9)
	private int rang;

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KompNiveau [");
		builder.append(super.toString());
		builder.append(", rang=");
		builder.append(rang);
		builder.append("]");
		return builder.toString();
	}

}
