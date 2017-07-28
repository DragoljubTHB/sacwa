package de.thb.fbi.project.generic.repository.api;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface BaseRepository<T> {
	public void create(T objectWithoutId);

	public void delete(int id);
	
	public void delete(@NotNull T objectWithoutId);
	
	public void update(@NotNull T objectWithoutId);

	public T find(int id);

	public List<T> findAll();

	

}
