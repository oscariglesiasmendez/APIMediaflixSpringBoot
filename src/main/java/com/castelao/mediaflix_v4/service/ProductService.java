package com.castelao.mediaflix_v4.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.entities.ProductType;
import com.castelao.mediaflix_v4.mapper.ProductMapper;
import com.castelao.mediaflix_v4.repository.ProductRepository;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public ProductPageDto getAllProductsPage(int page, int size) {
		Page<Product> productPage = productRepository.findAllProductsPage(PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public ProductDto findById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();

			return ProductMapper.toDto(product);
		} else {
			LOG.info("Product no encontrado: " + id);
			return null;
		}
	}

	/**
	 * Si el id del producto recibido existe, actualiza el mismo con los campos
	 * recibidos en productoDto
	 * 
	 * Devuelve el producto actualizado
	 * 
	 * Sino existe devuelve Optional.empty()
	 * 
	 * @param id           del producto a buscar
	 * @param libroDetails objeto con todos los campos a sobreescribir en la entidad
	 * @return Optional<ProductoDto>
	 */
	public Optional<ProductDto> update(Long id, ProductDto productoDto) {
		Optional<Product> optionalProducto = productRepository.findById(id);
		if (optionalProducto.isPresent()) {
			Product producto = optionalProducto.get();

			modelMapper.getConfiguration().setSkipNullEnabled(true).setSkipNullEnabled(true);
			// Copiar propiedades desde el objeto producto a la entidad
			modelMapper.map(productoDto, producto);

			Product productoSaved = productRepository.save(producto);
			return Optional.of(ProductMapper.toDto(productoSaved));
		} else {
			LOG.info("producto no encontrado: " + id);
			return Optional.empty();
		}
	}

	/**
	 * Si existe el producto con el id recibido como argumento cambia el campo
	 * vigente a false y devuelve true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del producto a desactivar
	 * @return true / false
	 */
	public boolean deactivateProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.deactivateProduct(id);
			return true;
		} else {
			LOG.info("Producto no encontrado: ", id);
			return false;
		}
	}

	/**
	 * Si existe el producto con el id recibido como argumento cambia el campo
	 * vigente a true y devuelve true
	 * 
	 * Sino existe devuelve falso
	 * 
	 * @param id del producto a activar
	 * @return true / false
	 */
	public boolean activateProduct(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			productRepository.activateProduct(id);
			return true;
		} else {
			LOG.info("Producto no encontrado: ", id);
			return false;
		}
	}

	/**
	 * Busca los productos por género
	 * 
	 * @param genre, page, size
	 * @return
	 */
	public ProductPageDto searchByGenre(String genre, int page, int size) {
		Page<Product> productPage = productRepository.findByGenre(genre, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos por language
	 * 
	 * @param genero
	 * @return
	 */
	public ProductPageDto searchByLanguage(String language, int page, int size) {
		Page<Product> productPage = productRepository.findByLanguage(language, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	public ProductPageDto searchByProductType(ProductType productType, int page, int size) {
		Page<Product> productPage = productRepository.findByProductType(productType, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos por precio mayor o igual que el recibido por parámetros
	 * 
	 * @param price, page, size
	 * @return
	 */
	public ProductPageDto searchByPrice(BigDecimal price, int page, int size) {
		Page<Product> productPage = productRepository.findByPriceGreaterThanEqual(price, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos vigentes
	 * 
	 * @param
	 * @return
	 */
	public ProductPageDto searchByAvailable(int page, int size) {
		Page<Product> productPage = productRepository.findByAvailable(PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos NO vigentes
	 * 
	 * @param
	 * @return
	 */
	public ProductPageDto searchByNotAvailable(int page, int size) {
		Page<Product> productPage = productRepository.findByNotAvailable(PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos con un titulo similar y paginado
	 * 
	 * @param title, page, size
	 * @return
	 */
	public ProductPageDto searchByTitle(String title, int page, int size) {
		Page<Product> productPage = productRepository.findByTitle(title, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos con un titulo similar
	 * 
	 * @param title
	 * @return
	 */
	public List<Product> searchByTitle(String title) {
		List<Product> productos = productRepository.findByTitleWithoutPagination(title);

		return productos;
	}

	/**
	 * Busca los productos con una puntuación mayor o igual, y paginado
	 * 
	 * @param rating, page, size
	 * @return
	 */
	public ProductPageDto searchByRating(BigDecimal rating, int page, int size) {
		Page<Product> productPage = productRepository.findByRatingGreaterThanEqual(rating, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	/**
	 * Busca los productos con un stock mayor o igual, y paginado
	 * 
	 * @param stock, page, size
	 * @return
	 */
	public ProductPageDto searchByStock(Integer stock, int page, int size) {
		Page<Product> productPage = productRepository.findByStockGreaterThanEqual(stock, PageRequest.of(page, size));
		return ProductMapper.toProductPageDto(productPage);
	}

	
	public List<Product> findAllProductsZeroStock() {
		return productRepository.findAllProductsZeroStock();
	}

	
	public List<Product> findAllProductsStockBetween1And5() {
		return productRepository.findAllProductsStockBetween1And5();
	}

//	public List<Product> getAllProducts() {
//  return productRepository.findAll();
//}

//	/**
//	 * Busca los productos por género sin paginación
//	 * 
//	 * @param genre
//	 * @return
//	 */
//	public List<ProductDto> searchByGenre(String genre) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> products = productRepository.findByGenreWithoutPagination(genre);
//
//		if (products != null) {
//			dtos = ProductMapper.toDto(products);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos por language
//	 * 
//	 * @param genero
//	 * @return
//	 */
//	public List<ProductDto> searchByLanguage(String language) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> products = productRepository.findByLanguageWithoutPagination(language);
//
//		if (products != null) {
//			dtos = ProductMapper.toDto(products);
//		}
//
//		return dtos;
//	}

	/**
	 * Busca los productos por Tipo de producto
	 * 
	 * @param ProductType
	 * @return
	 */

//	/**
//	 * Busca los productos por Tipo de producto
//	 * 
//	 * @param ProductType
//	 * @return
//	 */
//	public List<ProductDto> searchByProductType(ProductType productType) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> productos = productRepository.findByProductTypeWithoutPagination(productType);
//
//		if (productos != null) {
//			dtos = ProductMapper.toDto(productos);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos por precio mayor o igual que el recibido por parámetros
//	 * 
//	 * @param price
//	 * @return
//	 */
//	public List<ProductDto> searchByPrice(BigDecimal price) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> products = productRepository.findByPriceGreaterThanEqualWithoutPagination(price);
//
//		if (products != null) {
//			dtos = ProductMapper.toDto(products);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos vigentes
//	 * 
//	 * @param
//	 * @return
//	 */
//	public List<ProductDto> searchByAvailable() {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> productos = productRepository.findByAvailableWithoutPagination();
//
//		if (productos != null) {
//			dtos = ProductMapper.toDto(productos);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos NO vigentes
//	 * 
//	 * @param
//	 * @return
//	 */
//	public List<ProductDto> searchByNotAvailable() {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> productos = productRepository.findByNotAvailableWithoutPagination();
//
//		if (productos != null) {
//			dtos = ProductMapper.toDto(productos);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos con un titulo similar
//	 * 
//	 * @param title
//	 * @return
//	 */
//	public List<ProductDto> searchByTitle(String title) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> productos = productRepository.findByTitleWithoutPagination(title);
//
//		if (productos != null) {
//			dtos = ProductMapper.toDto(productos);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos con una puntuación mayor o igual
//	 * 
//	 * @param rating
//	 * @return
//	 */
//	public List<ProductDto> searchByRating(BigDecimal rating) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> products = productRepository.findByRatingGreaterThanEqualWithoutPagination(rating);
//
//		if (products != null) {
//			dtos = ProductMapper.toDto(products);
//		}
//
//		return dtos;
//	}

//	/**
//	 * Busca los productos con un stock mayor o igual
//	 * 
//	 * @param stock
//	 * @return
//	 */
//	public List<ProductDto> searchByStock(Integer stock) {
//		List<ProductDto> dtos = new ArrayList<ProductDto>();
//		List<Product> products = productRepository.findByStockGreaterThanEqualWithoutPagination(stock);
//
//		if (products != null) {
//			dtos = ProductMapper.toDto(products);
//		}
//
//		return dtos;
//	}

}
