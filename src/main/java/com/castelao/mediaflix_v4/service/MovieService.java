package com.castelao.mediaflix_v4.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.castelao.mediaflix_v4.dto.BookDto;
import com.castelao.mediaflix_v4.dto.MovieDto;
import com.castelao.mediaflix_v4.dto.pages.MoviePageDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.Movie;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.mapper.BookMapper;
import com.castelao.mediaflix_v4.mapper.MovieMapper;
import com.castelao.mediaflix_v4.mapper.ProductMapper;
import com.castelao.mediaflix_v4.repository.MovieRepository;

@Service
public class MovieService {

	private static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	private MovieRepository movieRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public MoviePageDto getAllMovies(int page, int size) {
		Page<Movie> moviePage = movieRepository.findAll(PageRequest.of(page, size));
		return MovieMapper.toMoviePageDto(moviePage);
	}
	
	public List<Movie> getAllMoviesWithoutPagination() {
		return movieRepository.findAll();
	}
	
	public MovieDto findById(Long id) {
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		if (optionalMovie.isPresent()) {
			Movie movie = optionalMovie.get();

			return MovieMapper.toDto(movie);
		} else {
			LOG.info("Movie no encontrado: " + id);
			return null;
		}
	}

	
	/**
	 * Crea una nueva película. Si ya existe otra con el mismo nombre lanza una
	 * excepcion DataIntegrityViolationException
	 * 
	 * Devuelve la peliculaDto recien creada
	 * 
	 * @param peliculaDto
	 * @return
	 * @throws DataIntegrityViolationException
	 */
	public MovieDto createMovie(MovieDto peliculaDto) throws DataIntegrityViolationException {
		if (movieRepository.existsByTitle(peliculaDto.getTitle())) {
			LOG.error("Pelicula con nombre ya existente: " + peliculaDto.getTitle());
			throw new DataIntegrityViolationException("Pelicula con nombre ya existente: " + peliculaDto.getTitle());
		}
		Movie pelicula = MovieMapper.toEntity(peliculaDto);
		pelicula = movieRepository.save(pelicula);
		MovieDto dtoCreated = MovieMapper.toDto(pelicula);
		return dtoCreated;
	}
	
	
	
	/**
	 * Si el id de la peliculaDto recibida existe, actualiza la misma con los campos
	 * recibidos en peliculaDto
	 * 
	 * Devuelve la pelicula actualizada
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id              de la pelicula a buscar
	 * @param peliculaDetails    objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<PeliculaDto>
	 */
	public Optional<MovieDto> update(Long id, MovieDto peliculaDto) {
		Optional<Movie> optionalPelicula = movieRepository.findById(id);
		if (optionalPelicula.isPresent()) {
			Movie pelicula = optionalPelicula.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto película a la entidad
			modelMapper.map(peliculaDto, pelicula);

			Movie peliculaSaved = movieRepository.save(pelicula);
			return Optional.of(MovieMapper.toDto(peliculaSaved));
		} else {
			LOG.info("pelicula no encontrada: " + id);
			return Optional.empty();
		}
	}
	
	
	
	public Optional<Movie> getById(Long id) {
		return movieRepository.findById(id);
	}
	
	
	/**
	 * Busca películas que pertenezcan al director recibido como argumento
	 * 
	 * @param director
	 * @return
	 */
	public MoviePageDto searchByDirector(String director, int page, int size) {
		Page<Movie> moviePage = movieRepository.findByDirector(director, PageRequest.of(page, size));
		return MovieMapper.toMoviePageDto(moviePage);
	}
	
	
	/**
	 * Busca películas que pertenezcan al estudio recibido como argumento
	 * 
	 * @param studio
	 * @return
	 */
	public MoviePageDto searchByStudio(String studio, int page, int size) {
		Page<Movie> moviePage = movieRepository.findByStudio(studio, PageRequest.of(page, size));
		return MovieMapper.toMoviePageDto(moviePage);
	}
	
	
	/**
	 * Busca películas que contengan un título similar al recibido como argumento
	 * 
	 * @param title
	 * @return
	 */
	public MoviePageDto searchByTitle(String title, int page, int size) {
		Page<Movie> moviePage = movieRepository.findByTitle(title, PageRequest.of(page, size));
		return MovieMapper.toMoviePageDto(moviePage);
	}
	
	/**
	 * Busca las películas con un titulo similar
	 * 
	 * @param title
	 * @return
	 */
	public List<Movie> searchByTitle(String title) {
		List<Movie> movies = movieRepository.findByTitleWithoutPagination(title);
		
		return movies;
	}
	
	
	/**
	 * Busca películas que tengan una duración mayor a la que recibe como argumento
	 * 
	 * @param duracion
	 * @return
	 */
	public MoviePageDto searchByDuracion(Integer duracion, int page, int size) {
		Page<Movie> moviePage = movieRepository.findByDuracionGreaterThan(duracion, PageRequest.of(page, size));
		return MovieMapper.toMoviePageDto(moviePage);
	}
	
	
	
}
