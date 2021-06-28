package fr.eni.projet.dal;

import java.util.List;

/**
 * Interface générique répresentant un DAO
 * @author pconchou2021
 *
 */


public interface DAO <T> {
	
	public void insert(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public List<T> selectAll();

	public T selectById(int id);
}
