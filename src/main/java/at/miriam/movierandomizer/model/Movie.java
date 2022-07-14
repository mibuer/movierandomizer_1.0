package at.miriam.movierandomizer.model;

import java.util.Objects;

public class Movie {
	
	
	private String title;
	private String director;
	private Genre genre;
	private String year;
	private StreamingService streamingService;
	private boolean watched = false;
	
	
	
	public Movie() {
		super();
	}

	public Movie(String title, String director, Genre genre, String year, StreamingService streamingService, boolean watched) {
		super();
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.year = year;
		this.streamingService = streamingService;
		this.watched = watched;
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
		return title + ", " + director + ", " + genre + ", " + year
				+ ", " + streamingService + ", "+  watched + "\n";
	}
	
	
	
	
	

}
