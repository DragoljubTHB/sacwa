package de.thb.fbi.project.koma.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import de.thb.fbi.project.generic.data.NamedEntity;

@Entity
public class KompBereich extends NamedEntity {

	/*
	 * entweder List -> List 
	 * oder @LazyColletion(LazyCollectionOption.FALSE) (	 * nicht im pom enthalten
	 */
	@OneToMany( cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Kompetenz> kompetenzen;

	@ManyToMany(mappedBy = "kompBereiche", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }) // schluesselKompetenzen
	private Set<SchluesselKomp> schluesselKompetenzen;

	public List<Kompetenz> getKompetenzen() {
		return kompetenzen;
	}

	public void setKompetenzen(List<Kompetenz> kompetenzen) {
		this.kompetenzen = kompetenzen;
	}

	public List<SchluesselKomp> getSchluesselKopetenzen() {
		return new ArrayList<>(schluesselKompetenzen);
	}

	public void setSchluesselKopetenzen(List<SchluesselKomp> schluesselKopetenzen) {
		this.schluesselKompetenzen.addAll(schluesselKopetenzen);
	}

	/*
	 * @Override public String toString() { StringBuilder builder = new
	 * StringBuilder(); builder.append("KompBereich [");
	 * builder.append(super.toString()); builder.append(", kompetenzen=");
	 * builder.append(kompetenzen); builder.append(", schluesselKopetenzen=");
	 * builder.append(schluesselKompetenzen); builder.append("]"); return
	 * builder.toString(); }
	 */

}
