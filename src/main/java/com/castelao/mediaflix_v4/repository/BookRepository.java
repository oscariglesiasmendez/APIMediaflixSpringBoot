package com.castelao.mediaflix_v4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT b FROM Book b")
    Page<Book> findAllBooksPage(Pageable pageable);
	
	// Buscar libros por autor
	@Query("SELECT b FROM Book b WHERE b.author LIKE %:author%")
	Page<Book> findByAuthor(String author, Pageable pageable);

	// Buscar libros por editorial
	@Query("SELECT b FROM Book b WHERE b.publisher LIKE %:publisher%")
	Page<Book> findByPublisher(String publisher, Pageable pageable);

	// Buscar libros con número de páginas mayor o igual a un valor
	@Query("SELECT b FROM Book b WHERE b.pageNumber >= :pageNumber")
	Page<Book> findByPageNumberGreaterThanEqual(Integer pageNumber, Pageable pageable);

	// Buscar libros por nombre
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	Page<Book> findByTitle(String title, Pageable pageable);

//	@Query("SELECT * FROM product INNER JOIN book ON product.product_id = book.product_id WHERE product.available = false")
//    Page<Book> findAvailableBooks(Pageable pageable);
//	
//	@Query("SELECT * FROM product INNER JOIN book ON product.product_id = book.product_id WHERE product.available = false")
//    Page<Book> findNotAvailableBooks(Pageable pageable);
//	
	@Query("SELECT COUNT(b) > 0 FROM Book b WHERE b.isbn = :isbn")
	boolean existsByIsbn(@Param("isbn") Long isbn);

	
//	// Método para desactivar un libro estableciendo el campo vigente a false
//	@Transactional
//	@Modifying
//	@Query("UPDATE Book b SET b.available = false WHERE b.id = :id")
//	void deactivateBook(@Param("id") Long id);
//
//	// Método para activar un libro estableciendo el campo vigente a true
//	@Transactional
//	@Modifying
//	@Query("UPDATE Book b SET b.available = true WHERE b.id = :id")
//	void activateBook(@Param("id") Long id);
	
//	Buscar libros por autor (sin paginación)
//	@Query("SELECT b FROM Book b WHERE b.author LIKE %:author%")
//	List<Book> findByAuthorWithoutPagination(@Param("author") String author);
//
//	Buscar libros por editorial (sin paginación)
//	@Query("SELECT b FROM Book b WHERE b.publisher LIKE %:publisher%")
//	List<Book> findByPublisherWithoutPagination(@Param("publisher") String publisher);
//
//	Buscar libros con número de páginas mayor o igual a un valor (sin paginación)
//	@Query("SELECT b FROM Book b WHERE b.pageNumber >= :pageNumber")
//	List<Book> findByPageNumberGreaterThanEqualWithoutPagination(@Param("pageNumber") Integer pageNumber);
//
//  Buscar libros por nombre (sin paginación)
//	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
//	List<Book> findByTitleWithoutPagination(@Param("title") String title);
//	
//	Buscar libros con el campo vigente establecido a true
//	@Query("SELECT b FROM Book b WHERE b.available = true")
//	List<Book> findAvailableBooks();
//
//	Buscar libros con el campo vigente establecido a false
//	@Query("SELECT b FROM Book b WHERE b.available = false")
//	List<Book> findNotAvailableBooks();

}
