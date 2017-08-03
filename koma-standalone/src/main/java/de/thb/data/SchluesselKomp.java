package de.thb.data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class SchluesselKomp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	private Set<KompBereich> kompBereiche;

	public Set<KompBereich> getKompBereiche() {
		return kompBereiche;
	}

	public void setKompBereiche(Set<KompBereich> kompBereiche) {
		this.kompBereiche = kompBereiche;
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

		SchluesselKomp that = (SchluesselKomp) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (kommentar != null ? !kommentar.equals(that.kommentar) : that.kommentar != null) return false;
		return kompBereiche != null ? kompBereiche.equals(that.kompBereiche) : that.kompBereiche == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (kommentar != null ? kommentar.hashCode() : 0);
		result = 31 * result + (kompBereiche != null ? kompBereiche.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SchluesselKomp{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kommentar='" + kommentar + '\'' +
				", kompBereiche=" + kompBereiche +
				'}';
	}
}