package com.castelao.mediaflix_v4.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.entities.ProductType;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p")
	Page<Product> findAllProductsPage(Pageable pageable);

	// Busca productos por género
	@Query("SELECT p FROM Product p WHERE p.genre LIKE %:genre%")
	Page<Product> findByGenre(String genre, Pageable pageable);

	// Buscar productos por idioma
	@Query("SELECT p FROM Product p WHERE p.language LIKE %:language%")
	Page<Product> findByLanguage(String language, Pageable pageable);

	// Busca productos que estén vigentes
	@Query("SELECT p FROM Product p WHERE p.available = true")
	Page<Product> findByAvailable(Pageable pageable);

	// Busca productos que estén vigentes
	@Query("SELECT p FROM Product p WHERE p.available = false")
	Page<Product> findByNotAvailable(Pageable pageable);

	// Busca productos con un precio mayor o igual al especificado
	@Query("SELECT p FROM Product p WHERE p.price >= :price")
	Page<Product> findByPriceGreaterThanEqual(BigDecimal price, Pageable pageable);

	// Consulta personalizada para buscar productos por tipo
	@Query("SELECT p FROM Product p WHERE p.productType = :type")
	Page<Product> findByProductType(ProductType type, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.title LIKE %:title%")
	Page<Product> findByTitle(@Param("title") String title, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.rating >= :rating")
	Page<Product> findByRatingGreaterThanEqual(@Param("rating") BigDecimal rating, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.stock >= :stock")
	Page<Product> findByStockGreaterThanEqual(@Param("stock") Integer stock, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.title LIKE %:title%")
	List<Product> findByTitleWithoutPagination(@Param("title") String title);	
	
	@Query("SELECT p FROM Product p WHERE p.stock = 0")
	List<Product> findAllProductsZeroStock();
	
	@Query("SELECT p FROM Product p WHERE p.stock > 0 AND p.stock <= 5")
	List<Product> findAllProductsStockBetween1And5();
	
	
	@Query("select count(p)>0 from Product p where p.title = :title")
	boolean existByTitle(@Param("title") String title);

	// Método para desactivar un producto estableciendo el campo vigente a false
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.available = false WHERE p.productId = ?1")
	void deactivateProduct(Long id);

	// Método para activar un producto estableciendo el campo vigente a true
	@Transactional
	@Modifying
	@Query("UPDATE Product p SET p.available = true WHERE p.productId = ?1")
	void activateProduct(Long id);

//	// Busca productos por género
//	@Query("SELECT p FROM Product p WHERE p.genre LIKE %:genre%")
//	List<Product> findByGenreWithoutPagination(String genre);
//
//	// Buscar productos por idioma
//	@Query("SELECT p FROM Product p WHERE p.language LIKE %:language%")
//	List<Product> findByLanguageWithoutPagination(String language);
//
//	// Busca productos que estén vigentes
//	@Query("SELECT p FROM Product p WHERE p.available = true")
//	List<Product> findByAvailableWithoutPagination();
//
//	// Busca productos que estén vigentes
//	@Query("SELECT p FROM Product p WHERE p.available = false")
//	List<Product> findByNotAvailableWithoutPagination();
//
//	// Busca productos con un precio mayor o igual al especificado
//	@Query("SELECT p FROM Product p WHERE p.price >= :price")
//	List<Product> findByPriceGreaterThanEqualWithoutPagination(BigDecimal price);
//
//  Consulta personalizada para buscar productos por tipo
//	@Query("SELECT p FROM Product p WHERE p.productType = :type")
//  List<Product> findByProductTypeWithoutPagination(ProductType type);
//
//	@Query("SELECT p FROM Product p WHERE p.rating >= :rating")
//	List<Product> findByRatingGreaterThanEqualWithoutPagination(@Param("rating") BigDecimal rating);
//
//	@Query("SELECT p FROM Product p WHERE p.stock >= :stock")
//	List<Product> findByStockGreaterThanEqualWithoutPagination(@Param("stock") Integer stock);

}
