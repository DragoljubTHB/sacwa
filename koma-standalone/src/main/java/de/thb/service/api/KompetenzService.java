package de.thb.service.api;


import de.thb.data.KompBereich;

import java.util.Set;

public interface KompetenzService
{
	Set<KompBereich> findKompetenzBereicheByKompetenzId(int aId);
}

