package com.castelao.mediaflix_v4.service;

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
import com.castelao.mediaflix_v4.dto.GameDto;
import com.castelao.mediaflix_v4.dto.pages.GamePageDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.Game;
import com.castelao.mediaflix_v4.mapper.BookMapper;
import com.castelao.mediaflix_v4.mapper.GameMapper;
import com.castelao.mediaflix_v4.repository.GameRepository;

@Service
public class GameService {

	private static Logger LOG = LoggerFactory.getLogger(GameService.class);

	@Autowired
	private GameRepository gameRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public GamePageDto getAllGamesPage(int page, int size) {
		Page<Game> gamePage = gameRepository.findAll(PageRequest.of(page, size));
		return GameMapper.toGamePageDto(gamePage);
	}
	
	public GameDto findById(Long id) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			Game game = optionalGame.get();

			return GameMapper.toDto(game);
		} else {
			LOG.info("Game no encontrado: " + id);
			return null;
		}
	}
	
	
	public GameDto createGame(GameDto gameDto) {
    	if (gameRepository.existsByTitle(gameDto.getTitle())) {
			LOG.error("Game con nombre ya existente: " + gameDto.getTitle());
			throw new DataIntegrityViolationException("Game con nombre ya existente: " + gameDto.getTitle());
		}
    	
    	Game game = GameMapper.toEntity(gameDto);
    	game = gameRepository.save(game);
    	GameDto dtoCreated = GameMapper.toDto(game);
        return dtoCreated;
    }
	
	
	/**
	 * Si el id del videojuego recibido existe, actualiza el mismo con los campos
	 * recibidos en videojuegoDto
	 * 
	 * Devuelve el videojuego actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id              del videojuego a buscar
	 * @param videojuegoDetails    objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<VideojuegoDto>
	 */
	public Optional<GameDto> update(Long id, GameDto gameDto) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			Game game = optionalGame.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto videojuego a la entidad
			modelMapper.map(gameDto, game);

			Game videojuegoSaved = gameRepository.save(game);
			return Optional.of(GameMapper.toDto(videojuegoSaved));
		} else {
			LOG.info("game no encontrado: " + id);
			return Optional.empty();
		}
	}
	
	
	/**
	 * Busca videojuegos que pertenezcan al desarrollador recibido como argumento
	 * 
	 * @param developer
	 * @return
	 */
	public GamePageDto searchByDeveloper(String developer, int page, int size) {
		Page<Game> gamePage = gameRepository.findByDeveloper(developer, PageRequest.of(page, size));
		return GameMapper.toGamePageDto(gamePage);
	}
	
	
	/**
	 * Busca videojuegos que contengan un título similar al recibido como argumento
	 * 
	 * @param title
	 * @return
	 */
	public GamePageDto searchByTitle(String title, int page, int size) {
		Page<Game> gamePage = gameRepository.findByTitle(title, PageRequest.of(page, size));
		return GameMapper.toGamePageDto(gamePage);
	}
	
	
	/**
	 * Busca videojuegos que pertenezcan a la plataforma recibida como argumento
	 * 
	 * @param platform
	 * @return
	 */
	public GamePageDto searchByPlatform(String platform, int page, int size) {
		Page<Game> gamePage = gameRepository.findByPlatform(platform, PageRequest.of(page, size));
		return GameMapper.toGamePageDto(gamePage);
	}
	
	
	/**
	 * Busca videojuegos cuya duracion en horas sea mayor a la proporcionada
	 * 
	 * @param duration
	 * @return
	 */
	public GamePageDto searchByDuration(Integer duration, int page, int size) {
		Page<Game> gamePage = gameRepository.findByDurationGreaterThan(duration, PageRequest.of(page, size));
		return GameMapper.toGamePageDto(gamePage);
	}
	
	
	
	

}
