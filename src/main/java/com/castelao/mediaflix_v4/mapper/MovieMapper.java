package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.castelao.mediaflix_v4.dto.MovieDto;
import com.castelao.mediaflix_v4.dto.pages.BookPageDto;
import com.castelao.mediaflix_v4.dto.pages.MoviePageDto;
import com.castelao.mediaflix_v4.entities.Movie;

public class MovieMapper {

	public static List<MovieDto> toDto(List<Movie> entities) {
		List<MovieDto> dtos = new ArrayList<>();
		for (Movie entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	public static MovieDto toDto(Movie entity) {
		MovieDto dto = new MovieDto(entity.getProductId(), entity.getTitle(), entity.getDescription(), entity.getStock(), entity.getLanguage(),
				entity.getProductType(), entity.getPrice(), entity.getRating(), entity.getUrl(), entity.getGenre(),
				entity.getReleaseDate(), entity.getAvailable(), entity.getDirector(), entity.getDuration(),
				entity.getStudio(), entity.getUrlTrailer());
		return dto;
	}

	public static Movie toEntity(MovieDto dto) {
		Movie entity = new Movie(dto.getTitle(), dto.getDescription(), dto.getStock(), dto.getLanguage(),
				dto.getProductType(), dto.getPrice(), dto.getRating(), dto.getUrl(), dto.getGenre(),
				dto.getReleaseDate(), dto.getAvailable(), dto.getDirector(), dto.getDuration(), dto.getStudio(), dto.getUrlTrailer());
		return entity;
	}
	
	public static MoviePageDto toMoviePageDto(Page<Movie> page) {
		MoviePageDto moviePageDto = new MoviePageDto();
        moviePageDto.setMovies(toDto(page.getContent())); // getContent() para obtener la lista de movies
        moviePageDto.setTotal(page.getTotalElements()); // getTotalElements() para obtener el total de elementos
        moviePageDto.setSkip(page.getNumber() * page.getSize()); // getPageNumber() y getSize() para obtener el skip
        moviePageDto.setLimit(page.getSize()); // getSize() para obtener el límite
        return moviePageDto;
    }

}
