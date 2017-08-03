package de.thb.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Stunde {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;

	@NotNull
	@ManyToOne(cascade = { CascadeType.ALL })
	private Sequenz sequenz;

	@ManyToMany(cascade = { CascadeType.ALL })
	private Set<Fertigkeit> neueFertigkeiten;

	@ManyToMany(cascade = { CascadeType.ALL })
	private Set<Fertigkeit> zufestigendeFertigkeiten;

	private String inhalt;
	private int ordnung;

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public Sequenz getSequenz() {
		return sequenz;
	}

	public void setSequenz(Sequenz sequenz) {
		this.sequenz = sequenz;
	}

	public Set<Fertigkeit> getNeueFertigkeiten() {
		return neueFertigkeiten;
	}

	public void setNeueFertigkeiten(Set<Fertigkeit> neueFertigkeiten) {
		this.neueFertigkeiten = neueFertigkeiten;
	}

	public Set<Fertigkeit> getZufestigendeFertigkeiten() {
		return zufestigendeFertigkeiten;
	}

	public void setZufestigendeFertigkeiten(Set<Fertigkeit> zufestigendeFertigkeiten) {
		this.zufestigendeFertigkeiten = zufestigendeFertigkeiten;
	}

	public int getOrdnung() {
		return ordnung;
	}

	public void setOrdnung(int ordnung) {
		this.ordnung = ordnung;
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

		Stunde stunde = (Stunde) o;

		if (id != stunde.id) return false;
		if (ordnung != stunde.ordnung) return false;
		if (name != null ? !name.equals(stunde.name) : stunde.name != null) return false;
		if (kommentar != null ? !kommentar.equals(stunde.kommentar) : stunde.kommentar != null) return false;
		if (sequenz != null ? !sequenz.equals(stunde.sequenz) : stunde.sequenz != null) return false;
		if (neueFertigkeiten != null ? !neueFertigkeiten.equals(stunde.neueFertigkeiten) : stunde.neueFertigkeiten != null)
			return false;
		if (zufestigendeFertigkeiten != null ? !zufestigendeFertigkeiten.equals(stunde.zufestigendeFertigkeiten) : stunde.zufestigendeFertigkeiten != null)
			return false;
		return inhalt != null ? inhalt.equals(stunde.inhalt) : stunde.inhalt == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (kommentar != null ? kommentar.hashCode() : 0);
		result = 31 * result + (sequenz != null ? sequenz.hashCode() : 0);
		result = 31 * result + (neueFertigkeiten != null ? neueFertigkeiten.hashCode() : 0);
		result = 31 * result + (zufestigendeFertigkeiten != null ? zufestigendeFertigkeiten.hashCode() : 0);
		result = 31 * result + (inhalt != null ? inhalt.hashCode() : 0);
		result = 31 * result + ordnung;
		return result;
	}

	@Override
	public String toString() {
		return "Stunde{" +
				"id=" + id +
				", name='" + name + '\'' +
				", kommentar='" + kommentar + '\'' +
				", sequenz=" + sequenz +
				", neueFertigkeiten=" + neueFertigkeiten +
				", zufestigendeFertigkeiten=" + zufestigendeFertigkeiten +
				", inhalt='" + inhalt + '\'' +
				", ordnung=" + ordnung +
				'}';
	}
}
