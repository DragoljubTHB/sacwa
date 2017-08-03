package de.thb.service.api;


import de.thb.data.Kompetenz;

import java.util.Set;

public interface KompBereichService {

	Set<Kompetenz> findKompetenzenByBereich(int aId);
}
