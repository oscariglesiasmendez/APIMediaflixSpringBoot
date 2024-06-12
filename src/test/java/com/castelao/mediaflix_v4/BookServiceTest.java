package com.castelao.mediaflix_v4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.castelao.mediaflix_v4.dto.BookDto;
import com.castelao.mediaflix_v4.dto.pages.BookPageDto;
import com.castelao.mediaflix_v4.entities.Book;
import com.castelao.mediaflix_v4.entities.ProductType;
import com.castelao.mediaflix_v4.repository.BookRepository;
import com.castelao.mediaflix_v4.service.BookService;

import jakarta.validation.constraints.NotNull;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Book createBook(Long id, String title, Integer stock, String language, ProductType productType,
            Double price, Double rating, String url, String genre, Date releaseDate, Boolean available,
            Long isbn, String author, String publisher, Integer pageNumber) {
        return new Book(id, title, null, stock, language, productType, price, rating, url, genre, (java.sql.@NotNull Date) releaseDate,
                available, isbn, author, publisher, pageNumber);
    }

    @Test
    void testGetAllBooks() {
        Page<Book> booksPage = new PageImpl<>(Arrays.asList(
                createBook(1L, "Book 1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url1", "Fiction",
                        new Date(), true, 1234567890L, "Author 1", "Publisher 1", 200),
                createBook(2L, "Book 2", 5, "Spanish", ProductType.BOOK, 20.0, 4.0, "url2", "Non Fiction",
                        new Date(), true, 1234567891L, "Author 2", "Publisher 2", 300)));
        when(bookRepository.findAllBooksPage(any(PageRequest.class))).thenReturn(booksPage);

        BookPageDto result = bookService.getAllBooks(0, 10);

        assertNotNull(result);
        assertEquals(2, result.getBooks().size());
        assertEquals("Book 1", result.getBooks().get(0).getTitle());
    }

    @Test
    void testFindById() {
        Book book = createBook(1L, "Book 1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction",
                new Date(), true, 1234567890L, "Author 1", "Publisher 1", 200);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto result = bookService.findById(1L);

        assertNotNull(result);
        assertEquals("Book 1", result.getTitle());
    }

    @Test
    void testCreateBook() {
        BookDto bookDto = new BookDto(null, "New Book", "Description", 10, "English", ProductType.BOOK, 10.0, 4.5,
                "url", "Fiction", (java.sql.Date) new Date(), true, 1234567890L, "Author 1", "Publisher 1", 200);
        when(bookRepository.existsByIsbn(bookDto.getIsbn())).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book savedBook = invocation.getArgument(0);
            savedBook.setProductId(1L); // Simulate saving with ID set
            return savedBook;
        });

        BookDto result = bookService.createBook(bookDto);

        assertNotNull(result);
        assertEquals("New Book", result.getTitle());
        assertNotNull(result.getProductId());
    }

    @Test
    void testUpdate() {
        Book existingBook = createBook(1L, "Book 1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction",
                new Date(), true, 1234567890L, "Author 1", "Publisher 1", 200);
        BookDto updatedBookDto = new BookDto(1L, "Updated Book", "Updated Description", 15, "Spanish", ProductType.BOOK,
                15.0, 4.8, "url", "Drama", (java.sql.Date) new Date(), true, 1234567890L, "Author 2", "Publisher 2", 300);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<BookDto> result = bookService.update(1L, updatedBookDto);

        assertTrue(result.isPresent());
        assertEquals("Updated Book", result.get().getTitle());
        assertEquals("Author 2", result.get().getAuthor());
    }
}
