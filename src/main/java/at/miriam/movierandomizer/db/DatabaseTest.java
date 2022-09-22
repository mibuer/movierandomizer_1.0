package at.miriam.movierandomizer.db;

import java.sql.SQLException;
import java.time.LocalDate;

import at.miriam.movierandomizer.model.Genre;
import at.miriam.movierandomizer.model.Movie;
import at.miriam.movierandomizer.model.StreamingService;
import at.miriam.movierandomizer.model.ValidateDatabaseValues;
import at.miriam.movierandomizer.repository.MovieRepository;
import at.miriam.movierandomizer.repository.Repository;
import at.miriam.movierandomizer.repository.StreamingServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseTest {
	
	static MovieRepository movieRepository = new MovieRepository();
	static StreamingServiceRepository streamingRepository = new StreamingServiceRepository();
	
	static ValidateDatabaseValues validateDatabase = new ValidateDatabaseValues();
	
	
	public static void main (String[] args) throws SQLException {
		
		//dropAndCreateTables();
		
		
		
		testDatabase();
		
	}

	private static void dropAndCreateTables() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovieDB");
		EntityManager em = emf.createEntityManager();
		
		em.close();
		emf.close();
		
	}

	private static void testDatabase() throws SQLException {
		
		//Testdaten
//		StreamingService service1 = new StreamingService(0, "Neflix");
//		StreamingService service2 = new StreamingService(0, "AmazonPrime");
//		
//		Movie movie1 = new Movie(0, "Lord of the Rings 1", "Peter Jackson", Genre.FANTASY, "2001", service1, false, LocalDate.now());
//		Movie movie2 = new Movie(0, "Der Hobbit", "Peter Jackson", Genre.FANTASY, "2008", service2, true, LocalDate.now());
//		
//		System.out.println("+++++++++++++++++++++++Test Data +++++++++++++++++++++++++++++++++++");
//		System.out.println(movie1 + " " + movie2);
//		
//		
//		//Text ValidateDatabase Values
//		System.out.println("+++++++++++++++++++++++ Validate Database Values ++++++++++++++++++++++");
//		validate(movie1);
//		validate(movie2);
//		
//		//Test Create
//		System.out.println("+++++++++++++++++++++++++CREATE StreamingService ++++++++++++++++++++++++++");
//		//streamingRepository.create(service1);
//		//streamingRepository.create(service2);
//		
//		System.out.println("+++++++++++++++++++++++CREATE Movie ++++++++++++++++++++++++++++++++++");
//		movieRepository.create(movie1);
//		movieRepository.create(movie2);
//		
//		//Test READ ALL
//		System.out.println("++++++++++++++++++++++++++ READ ALL Repos +++++++++++++++++++++++++++++");
//		System.out.println("Movies: " + movieRepository.readAll());
//		System.out.println("StreamingServices: " + streamingRepository.readAll());
//		
//		
//		//Test READ with ID
//		System.out.println("++++++++++++++++++++++++++++ READ with ID +++++++++++++++++++++++++++++");
//		System.out.println(movieRepository.read(movie1.getMovieID()));
//		System.out.println(movieRepository.read(movie2.getMovieID()));
		
		//Test UPDATE
		
//		movie1.setDirector("Marry Poppins");
//		movieRepository.update(movie1);
//		System.out.println("Updated movie :" + movie1);
		
		//Test set another Streaming Service
		//movie2.setStreamingService(service1);
		
		//Test DELETE
		//System.out.println("+++++++++++++++++++++ DELETE Movie ++++++++++++++++++++++++++++++++++");
		//movieRepository.delete(movie2);
		//movieRepository.deleteWithID(52);
		//streamingRepository.deleteWithID(1552);
		//System.out.println(movieRepository.readAll());
		
		
	}

	private static void validate(Movie movie) throws SQLException {
		
		StreamingService existingService;
		if ((existingService = validateDatabase.validateStreamingService(movie.getStreamingService())) != null) {
			movie.setStreamingService(existingService);
		} else {
			streamingRepository.create(movie.getStreamingService());
		}
		
//		String existingMovieTitle;
//		if((existingMovieTitle = validateDatabase.validateMovieTitle(movie).getTitle()) != null) {
//			movie.setTitle(existingMovieTitle);
//		} else {
//			movieRepository.create(movie);
//		}
		
		
	}
	

}
