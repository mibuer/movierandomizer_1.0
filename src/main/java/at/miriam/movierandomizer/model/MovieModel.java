package at.miriam.movierandomizer.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import at.miriam.movierandomizer.repository.MovieRepository;
import at.miriam.movierandomizer.repository.StreamingServiceRepository;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class MovieModel {
	
	
	private MovieRepository movieRepository = new MovieRepository();
	private StreamingServiceRepository streamingRepository = new StreamingServiceRepository();
	
	private ValidateDatabaseValues dataValidation = new ValidateDatabaseValues();
	
				
	//moviesList ist nur Zwischenspeicher, die 3 finalen Filme werden der moviesDBList zugewiesen und moviesList wieder auf Null gestellt
	public final ObservableList<Movie> moviesList = FXCollections.observableArrayList();
	
	
	public final ObservableList<Movie> moviesDBList = FXCollections.observableArrayList();
	
	public final ObservableList<Genre> genreList = FXCollections.observableArrayList(Genre.ACTION, Genre.ANIME, Genre.COMMEDY, Genre.CRIME,
																			Genre.DRAMA, Genre.FANTASY, Genre.HORROR, Genre.ROMANCE, Genre.SCIFI, 
																			Genre.SUPERHERO, Genre.THRILLER, Genre.ZOMBIE);
	public final ObservableList<StreamingService> streamingServiceList = FXCollections.observableArrayList(new StreamingService(0, "Netflix"), new StreamingService(0, "Amazon Prime"), new StreamingService(0, "Disney+"),
																				new StreamingService(0,"Arte"), new StreamingService(0, "YouTube"));
	
	
	private final ObjectProperty<Movie> selectedMovie = new SimpleObjectProperty<>();
	
	public final ObjectProperty<Movie> selectedMovieProperty() {
		return this.selectedMovie;		
	}
	
	public final Movie getSelectedMovie() {
		return this.selectedMovieProperty().get();
	}
	
	public final void setSelectedMovie (final Movie selectedMovie) {
		this.selectedMovieProperty().set(selectedMovie);
	}
	
	
	public MovieModel() {
		//Movies aus der DB einlesen
		//und der jeweiligen ObservableList übergeben
		
	try {
		moviesDBList.addAll(movieRepository.readAll());
		streamingServiceList.addAll(streamingRepository.readAll());
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	//Movie
	moviesDBList.addListener(new ListChangeListener<Movie>() {

		@Override
		public void onChanged(Change<? extends Movie> c) {
			
			while (c.next()) {
				
				if(c.wasReplaced()) { //update
					for (Movie movie : c.getAddedSubList()) {
						
						try {
							
							createRelatedEntitiesIfNotExist(movie);
							
							movieRepository.update(movie);
							
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}
				} else if (c.wasAdded()) { //create
					for (Movie movie : c.getAddedSubList()) {
						
						try {
							//Validieren, ob director bereits vorhanden
							createRelatedEntitiesIfNotExist(movie);
							
							movieRepository.create(movie);
						
						
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					
				} else if (c.wasRemoved()) { //delete
					for (Movie movie : c.getRemoved()) {
						
						try {
							movieRepository.delete(movie);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					}
				}
			} //Ende while Loop
			
		} 
	
	});//Ende ChangeListener für Movie
		
	streamingServiceList.addListener(new ListChangeListener<StreamingService>() {

		@Override
		public void onChanged(Change<? extends StreamingService> c) {
			
			while (c.next()) {
				
				if(c.wasAdded()) {
					for (StreamingService service : c.getAddedSubList()) {
						
						//validate DB values 
						try {
							
							if (dataValidation.validateStreamingService(service) == null) {
							
								streamingRepository.create(service);
								
							}
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else if (c.wasReplaced()) {
						for (StreamingService service : c.getAddedSubList()) {
							
							try {
								
								if(dataValidation.validateStreamingService(service) == null) {
									
									streamingRepository.update(service);
									
								}
								
							} catch (SQLException e) {
								e.printStackTrace();
							}	
						}
				} else if (c.wasRemoved()) {
					for (StreamingService service : c.getAddedSubList()) {
						
						try {
							streamingRepository.delete(service);
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}

				}
	
			}

		}		
	});
	
	
	
		
	} // ------------------------------------------------------------------------------------------
	
	private void createRelatedEntitiesIfNotExist(Movie movie) throws SQLException {
		
		System.out.println("+++++++++++++++++++ In Method createRelatedEntitiesIfNotExist ++++++++++++++++++++++++");
		
		
		StreamingService existingService;
		if ((existingService = dataValidation.validateStreamingService(movie.getStreamingService())) != null) {
			movie.setStreamingService(existingService);
		} else {
			streamingRepository.create(movie.getStreamingService());
		}
		
		
	}
	
	
	

}
