package de.thb.service.api;

import de.thb.data.KompBereich;

import java.util.Set;

public interface SchluesselKompService  {

	Set<KompBereich> findFindKompetenzBereiche(int aId);

}
