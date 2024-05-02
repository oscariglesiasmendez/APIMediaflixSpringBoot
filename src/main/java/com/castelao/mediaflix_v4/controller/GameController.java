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

import com.castelao.mediaflix_v4.dto.GameDto;
import com.castelao.mediaflix_v4.dto.MovieDto;
import com.castelao.mediaflix_v4.dto.pages.GamePageDto;
import com.castelao.mediaflix_v4.entities.Game;
import com.castelao.mediaflix_v4.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/games")
public class GameController {

	@Autowired
    private GameService gameService;
	
	
	@Operation(summary = "Get all games")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }) })
	@GetMapping
	public GamePageDto findAllPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return gameService.getAllGamesPage(page, size);
	}
	
	
	@Operation(summary = "Get all games without pagination")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }) })
	@GetMapping("/all")
	public List<Game> findAll() {
		return gameService.getAllGamesWithoutPagination();
	}
	
	
	@Operation(summary = "Get a Game by its Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Game found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }) })
	@GetMapping("/{id}")
	public ResponseEntity<GameDto> findById(@PathVariable Long id) {
		GameDto gameDto = gameService.findById(id);
		if (gameDto != null) {
			return new ResponseEntity<>(gameDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@Operation(summary = "Create a games")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Game created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<GameDto> createFilm(@Valid @RequestBody GameDto gameDto) {
		GameDto dtoWithId = gameService.createGame(gameDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Update a games by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Game updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = GameDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Game not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long gameId,
			@Valid @RequestBody GameDto gameDto) {

		Optional<GameDto> gameDtoUpdated = gameService.update(gameId, gameDto);
		if (gameDtoUpdated.isPresent()) {
			return ResponseEntity.ok(gameDtoUpdated);
		} else {
			return responseNotFound(gameId);
		}
	}
	
	
	@Operation(summary = "Get all games with %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDto.class))) }) })
	@GetMapping(value = "/title")
	public GamePageDto searchByTitle(@RequestParam(name = "title") String title,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return gameService.searchByTitle(title, page, size);
	}
    
	
	
	@Operation(summary = "Get all films with %developer%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDto.class))) }) })
	@GetMapping(value = "/developer")
	public GamePageDto searchByDeveloper(@RequestParam(name = "developer") String developer,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return gameService.searchByTitle(developer, page, size);
	}
	
	
	@Operation(summary = "Get all games with %platform%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDto.class))) }) })
	@GetMapping(value = "/platform")
	public GamePageDto searchByPlatform(@RequestParam(name = "platform") String platform,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return gameService.searchByPlatform(platform, page, size);
	}
	
	
	@Operation(summary = "Get all games with %duration%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Games found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameDto.class))) }) })
	@GetMapping(value = "/duration")
	public GamePageDto searchByDuration(@RequestParam(name = "duration") Integer duration,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return gameService.searchByDuration(duration, page, size);
	}
	
	
	
	private ResponseEntity<?> responseNotFound(Long gameId) {
		String errorMessage = "Game with id '" + gameId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
	
	
}
