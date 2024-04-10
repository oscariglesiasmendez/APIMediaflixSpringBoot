package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.castelao.mediaflix_v4.dto.BookDto;
import com.castelao.mediaflix_v4.dto.pages.BookPageDto;
import com.castelao.mediaflix_v4.entities.Book;



public class BookMapper {

	public static List<BookDto> toDto(List<Book> entities) {
		List<BookDto> dtos = new ArrayList<>();
		for (Book entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	public static BookDto toDto(Book entity) {
		BookDto dto = new BookDto(entity.getProductId(),entity.getTitle(), entity.getDescription(), entity.getStock(), entity.getLanguage(),
				entity.getProductType(), entity.getPrice(), entity.getRating(), entity.getUrl(), entity.getGenre(),
				entity.getReleaseDate(), entity.getAvailable(), entity.getIsbn(), entity.getAuthor(),
				entity.getPublisher(), entity.getPageNumber());
		return dto;
	}

	public static Book toEntity(BookDto dto) {
		Book entity = new Book(dto.getTitle(), dto.getDescription(), dto.getStock(), dto.getLanguage(),
				dto.getProductType(), dto.getPrice(), dto.getRating(), dto.getUrl(), dto.getGenre(),
				dto.getReleaseDate(), dto.getAvailable(), dto.getIsbn(), dto.getAuthor(),
				dto.getPublisher(), dto.getPageNumber());
		return entity;
	}
	
	public static BookPageDto toBookPageDto(Page<Book> page) {
        BookPageDto bookPageDto = new BookPageDto();
        bookPageDto.setBooks(toDto(page.getContent())); // getContent() para obtener la lista de libros
        bookPageDto.setTotal(page.getTotalElements()); // getTotalElements() para obtener el total de elementos
        bookPageDto.setSkip(page.getNumber() * page.getSize()); // getPageNumber() y getSize() para obtener el skip
        bookPageDto.setLimit(page.getSize()); // getSize() para obtener el límite
        return bookPageDto;
    }
	
}


