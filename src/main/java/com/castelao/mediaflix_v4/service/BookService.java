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
import com.castelao.mediaflix_v4.dto.pages.BookPageDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.mapper.BookMapper;
import com.castelao.mediaflix_v4.mapper.ProductMapper;
import com.castelao.mediaflix_v4.repository.BookRepository;

@Service
public class BookService {

	private static Logger LOG = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepository bookRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public BookPageDto getAllBooks(int page, int size) {
		Page<Book> books = bookRepository.findAll(PageRequest.of(page, size));
		return BookMapper.toBookPageDto(books);
	}

	public BookDto findById(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();

			return BookMapper.toDto(book);
		} else {
			LOG.info("Book no encontrado: " + id);
			return null;
		}
	}

	public BookDto createBook(BookDto bookDto) {
		if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
			LOG.error("Libro con nombre ya existente: " + bookDto.getIsbn());
			throw new DataIntegrityViolationException("Libro con ISBN ya existente: " + bookDto.getIsbn());
		}

		Book libro = BookMapper.toEntity(bookDto);
		libro = bookRepository.save(libro);
		BookDto dtoCreated = BookMapper.toDto(libro);
		return dtoCreated;
	}

	/**
	 * Si el id del libro recibido existe, actualiza el mismo con los campos
	 * recibidos en libroDto
	 * 
	 * Devuelve el libro actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id           del libro a buscar
	 * @param libroDetails objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<LibroDto>
	 */
	public Optional<BookDto> update(Long id, BookDto libroDto) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto libro a la entidad
			modelMapper.map(libroDto, book);

			Book bookSaved = bookRepository.save(book);
			return Optional.of(BookMapper.toDto(bookSaved));
		} else {
			LOG.info("libro no encontrado: " + id);
			return Optional.empty();
		}
	}

	public Optional<Book> getById(Long id) {
		return bookRepository.findById(id);
	}

	/**
	 * Busca libros que pertenezcan al autor recibido como argumento
	 * 
	 * @param autor, page, size
	 * @return
	 */
	public BookPageDto searchByAuthorPagination(String author, int page, int size) {
		Page<Book> bookPage = bookRepository.findByAuthor(author, PageRequest.of(page, size));
		return BookMapper.toBookPageDto(bookPage);
	}

	/**
	 * Busca libros que pertenezcan a la editorial que se recibe
	 * 
	 * @param publisher, page, size
	 * @return
	 */
	public BookPageDto searchByPublisherPagination(String publisher, int page, int size) {
		Page<Book> bookPage = bookRepository.findByPublisher(publisher, PageRequest.of(page, size));
		return BookMapper.toBookPageDto(bookPage);
	}

	/**
	 * Busca libros que contienen el nombre que se recibe
	 * 
	 * @param title, page, size
	 * @return
	 */
	public BookPageDto searchByTitlePagination(String title, int page, int size) {
		Page<Book> bookPage = bookRepository.findByTitle(title, PageRequest.of(page, size));
		return BookMapper.toBookPageDto(bookPage);
	}

	/**
	 * Busca libros que tengan un número de páginas mayor o igual que el recibido
	 * 
	 * @param pageNumber, page, size
	 * @return
	 */
	public BookPageDto searchByPageNumberPagination(Integer pageNumber, int page, int size) {
		Page<Book> bookPage = bookRepository.findByPageNumberGreaterThanEqual(pageNumber, PageRequest.of(page, size));
		return BookMapper.toBookPageDto(bookPage);
	}

	/**
	 * Busca los libros vigentes
	 * 
	 * @param
	 * @return
	 */
//	public BookPageDto searchByAvailable(int page, int size) {
//        Page<Book> bookPage = bookRepository.findAvailableBooks(PageRequest.of(page, size));
//        return BookMapper.toBookPageDto(bookPage);
//    }
//	
//	
//	/**
//	 * Busca los libros NO vigentes
//	 * 
//	 * @param
//	 * @return
//	 */
//	public BookPageDto searchByNotAvailable(int page, int size) {
//        Page<Book> bookPage = bookRepository.findNotAvailableBooks(PageRequest.of(page, size));
//        return BookMapper.toBookPageDto(bookPage);
//    }

//	public List<Book> getAllBooks() {
//		return bookRepository.findAll();
//	}

//    /**
//	 * Busca libros que pertenezcan al autor recibido como argumento
//	 * 
//	 * @param autor
//	 * @return
//	 */
//	public List<BookDto> searchByAuthor(String author) {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> books = bookRepository.findByAuthorWithoutPagination(author);
//
//		if (books != null) {
//			dtos = BookMapper.toDto(books);
//		}
//
//		return dtos;
//	}	

//	/**
//	 * Busca libros que pertenezcan a la editorial que se recibe
//	 * 
//	 * @param publisher
//	 * @return
//	 */
//	public List<BookDto> searchByPublisher(String publisher) {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> books = bookRepository.findByPublisherWithoutPagination(publisher);
//
//		if (books != null) {
//			dtos = BookMapper.toDto(books);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca libros que contienen el nombre que se recibe
//	 * 
//	 * @param title
//	 * @return
//	 */
//	public List<BookDto> searchByTitle(String title) {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> books = bookRepository.findByTitleWithoutPagination(title);
//
//		if (books != null) {
//			dtos = BookMapper.toDto(books);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca libros que tengan un número de páginas mayor o igual que el recibido
//	 * 
//	 * @param pageNumber
//	 * @return
//	 */
//	public List<BookDto> searchByPageNumber(Integer pageNumber) {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> books = bookRepository.findByPageNumberGreaterThanEqualWithoutPagination(pageNumber);
//
//		if (books != null) {
//			dtos = BookMapper.toDto(books);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca libros que estén vigente altualmente
//	 * 
//	 * @param 
//	 * @return
//	 */
//	public List<BookDto> searchByVigente() {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> libros = bookRepository.findAvailableBooks();
//
//		if (libros != null) {
//			dtos = BookMapper.toDto(libros);
//		}
//
//		return dtos;
//	}
//	
//	
//	/**
//	 * Busca libros que estén NO vigente altualmente
//	 * 
//	 * @param 
//	 * @return
//	 */
//	public List<BookDto> searchByNoVigente() {
//		List<BookDto> dtos = new ArrayList<BookDto>();
//		List<Book> libros = bookRepository.findNotAvailableBooks();
//
//		if (libros != null) {
//			dtos = BookMapper.toDto(libros);
//		}
//
//		return dtos;
//	}

}
