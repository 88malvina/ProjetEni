package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;


/**
 * Interface générique répresentant un DAO
 * @author pconchou2021
 *
 */


public interface DAO <T> {
	
	public void insert(T t) throws SQLException;
	
	public void delete(T t);
	
	public void update(T t);
	
	public List<T> selectAll() throws SQLException;

	public T selectById(int id);
	
}
