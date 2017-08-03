package de.thb.data;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class Kompetenz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;


	@ManyToOne(cascade = { CascadeType.ALL })
	private KompBereich kompBereich;

	public KompBereich getKompBereich() {
		return kompBereich;
	}

	public void setKompBereich(KompBereich kompBereich) {
		this.kompBereich = kompBereich;
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

		Kompetenz kompetenz = (Kompetenz) o;

		if (id != kompetenz.id) return false;
		if (name != null ? !name.equals(kompetenz.name) : kompetenz.name != null) return false;
		if (kommentar != null ? !kommentar.equals(kompetenz.kommentar) : kompetenz.kommentar != null) return false;
		return kompBereich != null ? kompBereich.equals(kompetenz.kompBereich) : kompetenz.kompBereich == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (kommentar != null ? kommentar.hashCode() : 0);
		result = 31 * result + (kompBereich != null ? kompBereich.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Kompetenz{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kommentar='" + kommentar + '\'' +
				", kompBereich=" + kompBereich +
				'}';
	}
}
