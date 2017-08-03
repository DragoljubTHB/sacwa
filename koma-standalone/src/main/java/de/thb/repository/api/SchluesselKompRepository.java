package de.thb.repository.api;

import de.thb.data.KompBereich;

import java.util.Set;

public interface SchluesselKompRepository  {

	Set<KompBereich> findKompetenzBereiche(int aId);

	
	
}
