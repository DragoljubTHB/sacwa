package de.thb.fbi.project.koma.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class SchluesselKomp extends NamedEntity {

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private List<KompBereich> kompBereiche;

	public List<KompBereich> getKompBereiche() {
		return kompBereiche;
	}

	public void setKompBereiche(List<KompBereich> kompBereiche) {
		this.kompBereiche = kompBereiche;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SchluesselKomp [");
		builder.append(super.toString());
		builder.append(", kompBereiche=");
		builder.append(kompBereiche);
		builder.append("]");
		return builder.toString();
	}

}