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
import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.dto.pages.BookPageDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.Game;
import com.castelao.mediaflix_v4.mapper.BookMapper;
import com.castelao.mediaflix_v4.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    
    @Operation(summary = "Get all books")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }) })
	@GetMapping
	public BookPageDto findAllPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return bookService.getAllBooks(page, size);
	}
    
    @Operation(summary = "Get all books without pagination")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }) })
	@GetMapping("/all")
	public List<Book> findAll() {
		return bookService.getAllBooksWithoutPagination();
	}
    
    @Operation(summary = "Get a book by its Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Book found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }) })
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> findById(@PathVariable Long id) {
    	BookDto bookDto = bookService.findById(id);
		if (bookDto != null) {
			return new ResponseEntity<>(bookDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    @Operation(summary = "Get all books by %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/title")
	public List<Book> searchByTitleWithoutPagination(@RequestParam(name = "title") String title) {
		return bookService.searchByTitle(title);
	}
    
    
    @Operation(summary = "Create a book")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Book created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/add")
	public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto libroDto) {
    	BookDto dtoWithId = bookService.createBook(libroDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
	}
    
    
    
    @Operation(summary = "Update a book by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long libroId,
			@Valid @RequestBody BookDto libroDto) {

		Optional<BookDto> libroDtoUpdated = bookService.update(libroId, libroDto);
		if (libroDtoUpdated.isPresent()) {
			return ResponseEntity.ok(libroDtoUpdated);
		} else {
			return responseNotFound(libroId);
		}
	}
    
    
    @Operation(summary = "Get all books with %author%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDto.class))) }) })
	@GetMapping(value = "/author")
	public BookPageDto searchByAuthor(@RequestParam(name = "author") String author,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return bookService.searchByAuthorPagination(author, page, size);
	}
    
    
    @Operation(summary = "Get all books with %publisher%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDto.class))) }) })
	@GetMapping(value = "/publisher")
	public BookPageDto searchByPublisher(@RequestParam(name = "publisher") String publisher,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
    	return bookService.searchByPublisherPagination(publisher, page, size);
	}
    
    
    @Operation(summary = "Get all books with %pageNumber%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDto.class))) }) })
	@GetMapping(value = "/pageNumber")
	public BookPageDto searchByNumeroPaginas(@RequestParam(name = "pageNumber") Integer pageNumber,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
    	return bookService.searchByPageNumberPagination(pageNumber, page, size);
	}
    
    @Operation(summary = "Get all books with %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookDto.class))) }) })
	@GetMapping(value = "/title/pagination")
	public BookPageDto searchByTitle(@RequestParam(name = "title") String title,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
    	return bookService.searchByTitlePagination(title, page, size);
	}
    
    
    private ResponseEntity<?> responseNotFound(Long libroId) {
		String errorMessage = "Book with id '" + libroId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
    
    
    
    
}