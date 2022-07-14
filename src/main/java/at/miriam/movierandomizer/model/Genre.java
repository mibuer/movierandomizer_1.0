package at.miriam.movierandomizer.model;

public enum Genre {

	HORROR ("Horror"), FANTASY ("Fantasy"), SCIFI ("Science fiction"), ACTION ("Action"), DRAMA ("Drama"), CRIME ("Crime"), 
	COMMEDY ("Commedy"), THRILLER ("Thriller"), ROMANCE ("Romance"), ANIME ("Anime"), DOCUMENTARY ("Documentary"), SUPERHERO ("Super-Hero"), ZOMBIE ("Zombie"); 

	private String genre;
	
	
	private Genre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
