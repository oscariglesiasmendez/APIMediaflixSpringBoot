package com.castelao.mediaflix_v4.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.mediaflix_v4.dto.BookDto;
import com.castelao.mediaflix_v4.dto.MovieDto;
import com.castelao.mediaflix_v4.dto.pages.MoviePageDto;
import com.castelao.mediaflix_v4.entities.Movie;
import com.castelao.mediaflix_v4.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

	
	@Autowired
    private MovieService movieService;

    
    @Operation(summary = "Get all movies")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Movies found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }) })
	@GetMapping
	public MoviePageDto findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
    	return movieService.getAllMovies(page, size);
	}
    
    
    @Operation(summary = "Get all movies without pagination")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Movies found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }) })
	@GetMapping("/all")
	public List<Movie> findAllMoviesWithoutPagination() {
    	return movieService.getAllMoviesWithoutPagination();
	}
    
    
    @Operation(summary = "Get a Movie by its Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Movie found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }) })
	@GetMapping("/{id}")
	public ResponseEntity<MovieDto> findById(@PathVariable Long id) {
    	MovieDto movieDto = movieService.findById(id);
		if (movieDto != null) {
			return new ResponseEntity<>(movieDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
    
    @Operation(summary = "Create a movie")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Movie created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto peliculaDto) {
    	MovieDto dtoWithId = movieService.createMovie(peliculaDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}
	
    
    @Operation(summary = "Update a movie by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Movie updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Movie not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long movieId,
			@Valid @RequestBody MovieDto movieDto) {

		Optional<MovieDto> movieDtoUpdated = movieService.update(movieId, movieDto);
		if (movieDtoUpdated.isPresent()) {
			return ResponseEntity.ok(movieDtoUpdated);
		} else {
			return responseNotFound(movieId);
		}
	}
    
    
    @Operation(summary = "Get all movies with %director%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Movies found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))) }) })
	@GetMapping(value = "/director")
	public MoviePageDto searchByDirector(@RequestParam(name = "director") String director,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return movieService.searchByDirector(director, page, size);
	}
    
    
    @Operation(summary = "Get all films with %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Films found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))) }) })
	@GetMapping(value = "/title")
	public MoviePageDto searchByTitle(@RequestParam(name = "title") String title,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return movieService.searchByTitle(title, page, size);
	}
    
    
    @Operation(summary = "Get all films with %studio%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Films found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))) }) })
	@GetMapping(value = "/studio")
	public MoviePageDto searchByStudio(@RequestParam(name = "studio") String studio,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
    	return movieService.searchByStudio(studio, page, size);
	}
    
    
    @Operation(summary = "Get all films with %duration%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Films found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovieDto.class))) }) })
	@GetMapping(value = "/duration")
	public MoviePageDto searchByDuration(@RequestParam(name = "duration") Integer duration,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
    	return movieService.searchByDuracion(duration, page, size);
	}
    
    
    private ResponseEntity<?> responseNotFound(Long movieId) {
		String errorMessage = "Movie with id '" + movieId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
}
