package com.castelao.mediaflix_v4.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Product;
import com.castelao.mediaflix_v4.entities.ProductType;
import com.castelao.mediaflix_v4.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Get all products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	@GetMapping
	public ProductPageDto findAllPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return productService.getAllProductsPage(page, size);
	}

	@Operation(summary = "Get all products without pagination")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	@GetMapping("/all")
	public List<Product> findAll() {
		return productService.getAllProducts();
	}

	@Operation(summary = "Get all products without stock")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	@GetMapping("/nostock")
	public ResponseEntity<List<Product>> findAllProductsZeroStock() {
		List<Product> products = productService.findAllProductsZeroStock();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@Operation(summary = "Get all products with stock >=1 & <=5")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	@GetMapping("/lowstock")
	public ResponseEntity<List<Product>> findAllProductsStockBetween1And5() {
		List<Product> products = productService.findAllProductsStockBetween1And5();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@Operation(summary = "Get a product by its Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Product found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
		ProductDto productDto = productService.findById(id);
		if (productDto != null) {
			return new ResponseEntity<>(productDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@Operation(summary = "Get all products with mayor price than %price%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/price")
	public ProductPageDto searchByPricePagination(@RequestParam(name = "price") BigDecimal price,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByPrice(price, page, size);
	}

	@Operation(summary = "Get all products by %language%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/language")
	public ProductPageDto searchByLanguagePagination(@RequestParam(name = "language") String language,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByLanguage(language, page, size);
	}

	@Operation(summary = "Get all products by %type%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/type")
	public ProductPageDto searchByProductTypePagination(@RequestParam(name = "type") ProductType productType,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByProductType(productType, page, size);
	}

	@Operation(summary = "Get all products by %genre%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/genre")
	public ProductPageDto searchByGenrePagination(@RequestParam(name = "genre") String genre,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByGenre(genre, page, size);
	}

	@Operation(summary = "Get all products by %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/title/pagination")
	public ProductPageDto searchByTitlePagination(@RequestParam(name = "title") String title,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByTitle(title, page, size);
	}

	@Operation(summary = "Get all products by %title%")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/title")
	public List<Product> searchByTitle(@RequestParam(name = "title") String title) {
		return productService.searchByTitle(title);
	}

	@Operation(summary = "Get all available products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/available")
	public ProductPageDto searchAvailablePagination(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByAvailable(page, size);
	}

	@Operation(summary = "Get all NOT available products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
	@GetMapping(value = "/notavailable")
	public ProductPageDto searchNotAvailablePagination(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return productService.searchByNotAvailable(page, size);
	}

	@Operation(summary = "Deactivate a product by its id (change its availability)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product deactivated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping(value = "/deactivate/{id}")
	public ResponseEntity<?> deactivateProductPagination(@PathVariable("id") Long productoId) {
		boolean deactivated = productService.deactivateProduct(productoId);
		if (deactivated) {
			return ResponseEntity.ok().build();
		} else {
			return responseNotFound(productoId);
		}
	}

	@Operation(summary = "Reactivate a product by its id (change its availability)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product reactivated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PutMapping(value = "/activate/{id}")
	public ResponseEntity<?> activateProductPagination(@PathVariable("id") Long productoId) {
		boolean reactivated = productService.activateProduct(productoId);
		if (reactivated) {
			return ResponseEntity.ok().build();
		} else {
			return responseNotFound(productoId);
		}
	}

	private ResponseEntity<?> responseNotFound(Long libroId) {
		String errorMessage = "Product with id '" + libroId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}

//	@Operation(summary = "Get all products")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }) })
//	@GetMapping
//	public List<ProductDto> findAll() {
//		List<Product> productos = productService.getAllProducts();
//		return ProductMapper.toDto(productos);
//	}
//	@Operation(summary = "Get all products with mayor price than %price%")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/price")
//	public List<ProductDto> searchByPrice(@RequestParam(name = "price") BigDecimal price) {
//		List<ProductDto> dtos = productService.searchByPrice(price);
//		return dtos;
//	}
//	@Operation(summary = "Get all products by %language%")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/language")
//	public List<ProductDto> searchByLanguage(@RequestParam(name = "language") String language) {
//		List<ProductDto> dtos = productService.searchByLanguage(language);
//		return dtos;
//	}
//	@Operation(summary = "Get all products by %type%")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/type")
//	public List<ProductDto> searchByLanguage(@RequestParam(name = "type") ProductType productType) {
//		List<ProductDto> dtos = productService.searchByProductType(productType);
//		return dtos;
//	}
//	@Operation(summary = "Get all products by %genre%")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/genre")
//	public List<ProductDto> searchByGenre(@RequestParam(name = "genre") String genre) {
//		List<ProductDto> dtos = productService.searchByGenre(genre);
//		return dtos;
//	}
//	@Operation(summary = "Get all available products")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/available")
//	public List<ProductDto> searchAvailable() {
//		List<ProductDto> dtos = productService.searchByAvailable();
//		return dtos;
//	}
//	@Operation(summary = "Get all NOT available products")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Products found", content = {
//			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))) }) })
//	@GetMapping(value = "/notavailable")
//	public List<ProductDto> searchNotAvailable() {
//		List<ProductDto> dtos = productService.searchByNotAvailable();
//		return dtos;
//	}

}
