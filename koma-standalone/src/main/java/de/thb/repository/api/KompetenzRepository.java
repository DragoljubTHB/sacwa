package de.thb.repository.api;

import de.thb.data.KompBereich;

import java.util.Set;

public interface KompetenzRepository  {

	Set<KompBereich> findKompetenzBereicheByKompetenzId(int aId);

}
