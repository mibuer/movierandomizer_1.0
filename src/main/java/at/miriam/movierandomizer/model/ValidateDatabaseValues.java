package at.miriam.movierandomizer.model;

import at.miriam.movierandomizer.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ValidateDatabaseValues {

	private static EntityManager em = Repository.em;
	
	public StreamingService validateStreamingService (StreamingService service) {
		
		System.out.println("+++++++++++++ Method validateStreamingService ++++++++++++++++++");
		
		final String JPQL_SERVICE = "SELECT s FROM StreamingService s WHERE s.name = :name";
		
		TypedQuery<StreamingService> query = em.createQuery(JPQL_SERVICE, StreamingService.class);
		
		query.setParameter("name", service.getName());
		
		if(!query.getResultList().isEmpty()) {
			System.out.println("Query Result List: " + query.getResultList());
			System.out.println("Value found: " + service.getName());
			return query.getSingleResult();
		}
		
		return null;

	}
	
	public Movie validateMovieTitle (Movie movie) {
		
		System.out.println("+++++++++++++++++++ Method validateMovieTitle +++++++++++++++++++++++++");
		
		final String JPQL_MOVIE_TITLE = "SELECT m FROM Movie m WHERE m.title = :title";
		
		TypedQuery<Movie> query = em.createQuery(JPQL_MOVIE_TITLE, Movie.class);
		
		query.setParameter("title", movie.getTitle());
		
		if(!query.getResultList().isEmpty()) {
			System.out.println("Query Result List: " + query.getResultList());
			System.out.println("Value found: " + movie.getTitle());
			return query.getSingleResult();
		}
		
		return null;
	}
	
}
