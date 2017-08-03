package de.thb.repository.api;

import de.thb.data.Fertigkeit;
import de.thb.data.KompBereich;
import de.thb.data.Kompetenz;

import java.util.Set;

public interface KompBereichRepository {

	Set<KompBereich> findByKomp(Kompetenz kompetenz);

	Set<Fertigkeit> findBySchluesselKomp(Kompetenz komp);

	Set<Kompetenz> findKompetenzenByBereich(int aId);

}
