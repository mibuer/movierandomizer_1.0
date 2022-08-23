package at.miriam.movierandomizer.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import at.miriam.movierandomizer.model.Movie;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class MovieRepository implements Repository<Movie> {

	@Override
	public void create(Movie movie) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(movie);
		
		transaction.commit();
		
	}

	@Override
	public Optional<Movie> read(long id) throws SQLException {

		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Movie movie = em.find(Movie.class, id);
		
		transaction.commit();
		
		return Optional.ofNullable(movie);
	}

	@Override
	public List<Movie> readAll() throws SQLException {
		
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class); 
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		List<Movie> movies = query.getResultList();
		
		transaction.commit();
		
		return movies;
	}

	@Override
	public Movie update(Movie movie) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Movie updatedMovie = em.merge(movie);
		
		transaction.commit();

		return updatedMovie;
	}

	@Override
	public void delete(Movie movie) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.remove(movie);
		
		transaction.commit();
		
	}

	@Override
	public void deleteAll() throws SQLException {
		
		TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m", Movie.class);
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		List<Movie> movieToDelete = query.getResultList();
		
		for (Movie movie : movieToDelete) {
			
			em.remove(movie);
		}
		
		transaction.commit();
	}

	@Override
	public void deleteWithID(long id) throws SQLException {
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Movie movieToDelete = em.find(Movie.class, id);
		
		em.remove(movieToDelete);
		
		transaction.commit();
		
	}

}
