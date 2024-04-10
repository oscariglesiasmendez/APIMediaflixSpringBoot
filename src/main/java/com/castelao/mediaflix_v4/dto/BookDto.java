package com.castelao.mediaflix_v4.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.castelao.mediaflix_v4.entities.ProductType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
	
	private Long productId;
	private String title;
	private String description;
	private Integer stock;
	private String language;
	private ProductType productType;
	private Double price;
	private Double rating;
	private String url;
	private String genre;
	private Date releaseDate;
	private Boolean available;
	private Long isbn;
	private String author;
	private String publisher;
	private Integer pageNumber;

	
	public BookDto(Long bookId, String title, String description, Integer stock, String language, ProductType productType,
			Double price, Double rating, String url, String genre, Date releaseDate, Boolean available,
			Long isbn, String author, String publisher, Integer pageNumber) {
		this.productId = bookId;
		this.title = title;
		this.description = description;
		this.stock = stock;
		this.language = language;
		this.productType = productType;
		this.price = price;
		this.rating = rating;
		this.url = url;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.available = available;
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.pageNumber = pageNumber;
	}
	
//	public BookDto(String title, String description, Integer stock, String language, ProductType productType,
//			BigDecimal price, BigDecimal rating, String url, String genre, Date releaseDate, Boolean available,
//			Long isbn, String author, String publisher, Integer pageNumber) {
//		this.title = title;
//		this.description = description;
//		this.stock = stock;
//		this.language = language;
//		this.productType = productType;
//		this.price = price;
//		this.rating = rating;
//		this.url = url;
//		this.genre = genre;
//		this.releaseDate = releaseDate;
//		this.available = available;
//		this.isbn = isbn;
//		this.author = author;
//		this.publisher = publisher;
//		this.pageNumber = pageNumber;
//	}

}
