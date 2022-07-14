package at.miriam.movierandomizer.model;

public final class RandomMovie {
	
	private Movie randMovie;
	
	//Singelton: 
	private final static RandomMovie INSTANCE = new RandomMovie();
	
	//Private Konstuktor, damit keine neuen Instanzen erstellt werden können
	private RandomMovie() {
		
	}

	public static RandomMovie getInstance() {
		return INSTANCE;
	}

	public Movie getRandMovie() {
		return randMovie;
	}

	public void setRandMovie(Movie randMovie) {
		this.randMovie = randMovie;
	}
	
	

}
