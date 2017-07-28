package de.thb.fbi.project.generic.data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class NamedEntity extends GenericEntity {

	@NotNull(message = "Bitte mindestens ein Zeichen eingeben !")
	@Column(unique = true)
	@Size(min = 1, message = "Bitte mindestens ein Zeichen eingeben !")
	private String name;

	private String kommentar;

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(super.toString());
		builder.append(", name=");
		builder.append(name);
		builder.append(", kommentar=");
		builder.append(kommentar);
		builder.append("]");
		return builder.toString();
	}

}
