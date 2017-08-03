package de.thb.repository.api;

import de.thb.data.Fertigkeit;
import de.thb.data.Kompetenz;

import java.util.Set;

public interface FertigkeitRepository {

	Set<Fertigkeit> findByKompetenz(Kompetenz komp);

}
