package at.miriam.movierandomizer.repository;

import java.util.stream.Stream;

import at.miriam.movierandomizer.model.Genre;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter (autoApply = true)
public class GenreConverter implements AttributeConverter<Genre, String> {

	@Override
	public String convertToDatabaseColumn(Genre genre) {
		
		if (genre == null) {
			return null;
		}
		
		return genre.getGenre();
	}

	@Override
	public Genre convertToEntityAttribute(String genre) {
		
		if (genre == null) {
			return null;
		}
		
		return Stream.of(Genre.values())
				.filter(data -> data.getGenre().equals(genre))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
	

}
