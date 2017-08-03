package de.thb.data;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
public class Sequenz  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;

	private KlasseFachId klfaID;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "sequenz")
	@OrderBy("odnung")
	private Set<Stunde> stunden;

	private int ordnung;

	public int getOrdnung() {
		return ordnung;
	}

	public void setOrdnung(int ordnung) {
		this.ordnung = ordnung;
	}

	public Set<Stunde> getStunden() {
		return stunden;
	}

	public void setStunden(Set<Stunde> stunden) {
		this.stunden = stunden;
	}

	public KlasseFachId getKlfaID() {
		return klfaID;
	}

	public void setKlfaID(KlasseFachId klfaID) {
		this.klfaID = klfaID;
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

		Sequenz sequenz = (Sequenz) o;

		if (id != sequenz.id) return false;
		if (ordnung != sequenz.ordnung) return false;
		if (name != null ? !name.equals(sequenz.name) : sequenz.name != null) return false;
		if (kommentar != null ? !kommentar.equals(sequenz.kommentar) : sequenz.kommentar != null) return false;
		if (klfaID != null ? !klfaID.equals(sequenz.klfaID) : sequenz.klfaID != null) return false;
		return stunden != null ? stunden.equals(sequenz.stunden) : sequenz.stunden == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (kommentar != null ? kommentar.hashCode() : 0);
		result = 31 * result + (klfaID != null ? klfaID.hashCode() : 0);
		result = 31 * result + (stunden != null ? stunden.hashCode() : 0);
		result = 31 * result + ordnung;
		return result;
	}

	@Override
	public String toString() {
		return "Sequenz{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kommentar='" + kommentar + '\'' +
				", klfaID=" + klfaID +
				", stunden=" + stunden +
				", ordnung=" + ordnung +
				'}';
	}
}
