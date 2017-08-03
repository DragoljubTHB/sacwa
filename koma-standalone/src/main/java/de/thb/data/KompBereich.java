package de.thb.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class KompBereich  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;

	/*
	 * entweder Set -> Set
	 * oder @LazyColletion(LazyCollectionOption.FALSE) (	 * nicht im pom enthalten
	 */
	@OneToMany( cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Kompetenz> kompetenzen;

	@ManyToMany(mappedBy = "kompBereiche", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }) // schluesselKompetenzen
	private Set<SchluesselKomp> schluesselKompetenzen;

	public Set<Kompetenz> getKompetenzen() {
		return kompetenzen;
	}

	public void setKompetenzen(Set<Kompetenz> kompetenzen) {
		this.kompetenzen = kompetenzen;
	}

	public Set<SchluesselKomp> getSchluesselKopetenzen() {
		//todo: chech for hashset vs options
		return new HashSet<>(schluesselKompetenzen);
	}

	public void setSchluesselKopetenzen(Set<SchluesselKomp> schluesselKopetenzen) {
		this.schluesselKompetenzen.addAll(schluesselKopetenzen);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		KompBereich that = (KompBereich) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (kommentar != null ? !kommentar.equals(that.kommentar) : that.kommentar != null) return false;
		if (kompetenzen != null ? !kompetenzen.equals(that.kompetenzen) : that.kompetenzen != null) return false;
		return schluesselKompetenzen != null ? schluesselKompetenzen.equals(that.schluesselKompetenzen) : that.schluesselKompetenzen == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (kommentar != null ? kommentar.hashCode() : 0);
		result = 31 * result + (kompetenzen != null ? kompetenzen.hashCode() : 0);
		result = 31 * result + (schluesselKompetenzen != null ? schluesselKompetenzen.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "KompBereich{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kommentar='" + kommentar + '\'' +
				", kompetenzen=" + kompetenzen +
				", schluesselKompetenzen=" + schluesselKompetenzen +
				'}';
	}
}
