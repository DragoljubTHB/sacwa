package de.thb.repository.api;

import de.thb.data.Fach;
import de.thb.data.Fertigkeit;

import java.util.Set;

public interface FachRepository {
	Set<Fach> findByFertigkeit(Fertigkeit fertigkeit);

	Set<Fach> getAll();

	void create(Fach fach);
}
