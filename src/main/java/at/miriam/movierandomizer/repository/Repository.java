package at.miriam.movierandomizer.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public interface Repository<T> {

	public static EntityManager em = setupDatabaseConnection();

	
	public static EntityManager setupDatabaseConnection() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDB");
		return emf.createEntityManager();
	}
	
	
	
	void create (T entity) throws SQLException;
	
	Optional<T> read(long id) throws SQLException;
		
	List<T> readAll() throws SQLException;
		
	T update(T entity) throws SQLException;
		
	void delete(T entity) throws SQLException;
		
	void deleteAll() throws SQLException;
		
	void deleteWithID (long id) throws SQLException;
	
	
}
