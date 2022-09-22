package at.miriam.movierandomizer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "MOVIE")
public class Movie implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private long movieID;
	private String title;
	private String director;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private String year;
	@ManyToOne
	@JoinColumn (name = "FK_STREAMING_ID")
	private StreamingService streamingService;
	private boolean watched = false;
	private LocalDate currentDate = LocalDate.now();
	
	
	
	public Movie() {
		super();
	}

	public Movie(long movieID, String title, String director, Genre genre, String year, StreamingService streamingService, boolean watched, LocalDate currentDate) {
		super();
		this.setMovieID(movieID);
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.year = year;
		this.streamingService = streamingService;
		this.watched = watched;
		this.setCurrentDate(currentDate);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public StreamingService getStreamingService() {
		return streamingService;
	}

	public void setStreamingService(StreamingService streamingService) {
		this.streamingService = streamingService;
	}

	public boolean isWatched() {
		return watched;
	}

	public void setWatched(boolean watched) {
		this.watched = watched;
	}
	
	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public long getMovieID() {
		return movieID;
	}

	public void setMovieID(long movieID) {
		this.movieID = movieID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(director, genre, streamingService, title, watched, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(director, other.director) && genre == other.genre
				&& Objects.equals(streamingService, other.streamingService) && Objects.equals(title, other.title)
				&& watched == other.watched && Objects.equals(year, other.year);
	}

	@Override
	public String toString() {
		
		String df = currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
		
		return title + ", " + director + ", " + genre + ", " + year
				+ ", " + streamingService + ", "+  watched +  " " + df + "\n";
	}

	
	
	
	
	
	

}
