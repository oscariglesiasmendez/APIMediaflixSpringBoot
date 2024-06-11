package com.castelao.mediaflix_v4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.entities.ProductType;
import com.castelao.mediaflix_v4.repository.ProductRepository;
import com.castelao.mediaflix_v4.service.ProductService;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Product createProduct(Long id, String title, Integer stock, String language, ProductType productType, Double price, Double rating, String url, String genre, Date releaseDate, Boolean available) {
        Product product = new Product();
        product.setProductId(id);
        product.setTitle(title);
        product.setStock(stock);
        product.setLanguage(language);
        product.setProductType(productType);
        product.setPrice(price);
        product.setRating(rating);
        product.setUrl(url);
        product.setGenre(genre);
        product.setReleaseDate(releaseDate);
        product.setAvailable(available);
        return product;
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2023-01-01"), true),
            createProduct(2L, "Product2", 5, "Spanish", ProductType.BOOK, 20.0, 4.0, "url", "Non-Fiction", Date.valueOf("2023-02-01"), true)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getTitle());
    }

    @Test
    void testFindById() {
        Product product = createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDto result = productService.findById(1L);
        assertNotNull(result);
        assertEquals("Product1", result.getTitle());
    }

    @Test
    void testUpdate() {
        Product product = createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true);
        ProductDto productDto = new ProductDto(1L, "UpdatedProduct", "UpdatedDescription", 15, "Spanish", ProductType.BOOK, 15.0, 4.8, "url", "Drama", Date.valueOf("2024-04-01"), true);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Optional<ProductDto> result = productService.update(1L, productDto);
        assertTrue(result.isPresent());
        assertEquals("UpdatedProduct", result.get().getTitle());
    }
    
    @Test
    void testDeactivateProduct() {
        Product product = createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean result = productService.deactivateProduct(1L);
        assertTrue(result);
        verify(productRepository, times(1)).deactivateProduct(1L);
    }

    @Test
    void testActivateProduct() {
        Product product = createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), false);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        boolean result = productService.activateProduct(1L);
        assertTrue(result);
        verify(productRepository, times(1)).activateProduct(1L);
    }

    @Test
    void testSearchByGenre() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByGenre(anyString(), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByGenre("Fiction", 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByLanguage() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByLanguage(anyString(), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByLanguage("English", 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByProductType() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByProductType(any(ProductType.class), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByProductType(ProductType.BOOK, 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByPrice() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByPriceGreaterThanEqual(any(BigDecimal.class), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByPrice(BigDecimal.valueOf(10.0), 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByAvailable() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByAvailable(any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByAvailable(0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByNotAvailable() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), false)
        ));
        when(productRepository.findByNotAvailable(any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByNotAvailable(0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByTitlePaged() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByTitle(anyString(), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByTitle("Product1", 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }

    @Test
    void testSearchByTitleWithoutPagination() {
        List<Product> products = Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        );
        when(productRepository.findByTitleWithoutPagination(anyString())).thenReturn(products);

        List<Product> result = productService.searchByTitle("Product1");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getTitle());
    }

    @Test
    void testSearchByRating() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
            createProduct(1L, "Product1", 10, "English", ProductType.BOOK, 10.0, 4.5, "url", "Fiction", Date.valueOf("2024-04-01"), true)
        ));
        when(productRepository.findByRatingGreaterThanEqual(any(BigDecimal.class), any(PageRequest.class))).thenReturn(productPage);

        ProductPageDto result = productService.searchByRating(BigDecimal.valueOf(4.0), 0, 10);
        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertEquals("Product1", result.getProducts().get(0).getTitle());
    }


}