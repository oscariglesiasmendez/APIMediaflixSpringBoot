package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.castelao.mediaflix_v4.dto.GameDto;
import com.castelao.mediaflix_v4.dto.pages.GamePageDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Game;
import com.castelao.mediaflix_v4.entities.Product;

public class GameMapper {

	public static List<GameDto> toDto(List<Game> entities) {
		List<GameDto> dtos = new ArrayList<>();
		for (Game entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	public static GameDto toDto(Game entity) {
		GameDto dto = new GameDto(entity.getProductId(),entity.getTitle(), entity.getDescription(), entity.getStock(),
				entity.getLanguage(), entity.getProductType(), entity.getPrice(), entity.getRating(), entity.getUrl(),
				entity.getGenre(), entity.getReleaseDate(), entity.getAvailable(), entity.getDeveloper(),
				entity.getPlatform(), entity.getDuration(), entity.getUrlTrailer());
		return dto;
	}

	public static Game toEntity(GameDto dto) {
		Game entity = new Game(dto.getTitle(), dto.getDescription(), dto.getStock(),
				dto.getLanguage(), dto.getProductType(), dto.getPrice(), dto.getRating(), dto.getUrl(),
				dto.getGenre(), dto.getReleaseDate(), dto.getAvailable(), dto.getDeveloper(),
				dto.getPlatform(), dto.getDuration(), dto.getUrlTrailer());
		return entity;
	}

	
	public static GamePageDto toGamePageDto(Page<Game> page) {
		GamePageDto gamePageDto = new GamePageDto();
        gamePageDto.setGames(toDto(page.getContent())); // getContent() para obtener la lista de productos
        gamePageDto.setTotal(page.getTotalElements()); // getTotalElements() para obtener el total de elementos
        gamePageDto.setSkip(page.getNumber() * page.getSize()); // getPageNumber() y getSize() para obtener el skip
        gamePageDto.setLimit(page.getSize()); // getSize() para obtener el límite
        return gamePageDto;
    }
	
}
