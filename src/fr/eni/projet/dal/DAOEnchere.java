package fr.eni.projet.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.projet.bo.Enchere;

 
public interface DAOEnchere<T> extends DAO<T>{
public void insert(Enchere t);
	
	public void delete (Enchere t);
	
	public void update(Enchere t);
	
	public List<T> selectAll() throws SQLException;
	
	public T selectById(int id);
	
}
