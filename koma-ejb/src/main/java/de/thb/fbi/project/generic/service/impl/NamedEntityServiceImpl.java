package de.thb.fbi.project.generic.service.impl;

import de.thb.fbi.project.generic.data.NamedEntity;
import de.thb.fbi.project.generic.repository.api.NamedEntityRepository;
import de.thb.fbi.project.generic.service.api.NamedEntityService;

public abstract  class NamedEntityServiceImpl
<EntityType extends NamedEntity, NamedRepositoryType extends NamedEntityRepository<EntityType>> 
	extends GenericServiceImpl<EntityType, NamedRepositoryType>//TODO: to test without extending own repository & Service CRUD 4 Named Entities
	implements NamedEntityService<EntityType>
{

	private NamedEntityRepository<EntityType> neri;

//	@Override
//	public NamedEntity findByName(String name) {
//		return neri.checkIfNameExists(name);
//	}

	@SuppressWarnings("unchecked")//FIXME:unchecked
	@Override
	protected NamedRepositoryType getRepositoryType() {

		return (NamedRepositoryType) neri;
	}
	
	

	
	
}
