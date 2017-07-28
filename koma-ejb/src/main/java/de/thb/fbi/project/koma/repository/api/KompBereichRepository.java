package de.thb.fbi.project.koma.repository.api;

import java.util.List;

import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.koma.data.Fertigkeit;
import de.thb.fbi.project.koma.data.KompBereich;
import de.thb.fbi.project.koma.data.Kompetenz;

public interface KompBereichRepository extends NamedEntityRepository<KompBereich> {

	List<KompBereich> findByKomp(Kompetenz kompetenz);

	List<Fertigkeit> findBySchluesselKomp(Kompetenz komp);

	List<Kompetenz> findKompetenzenByBereich(int aId);

}
