package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book extends Product{

	@NotNull
	private Long isbn;
	
	@NotNull
	private String author;

	private String publisher;

	@Column(name = "page_number")
	private Integer pageNumber;

	public Book(Long productId, @NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull Long isbn,
			@NotNull String author, String publisher, Integer pageNumber) {
		super(productId, title, description, stock, language, productType, price, rating, url, genre, releaseDate,
				available);
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.pageNumber = pageNumber;
	}

	public Book(@NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull Long isbn,
			@NotNull String author, String publisher, Integer pageNumber) {
		super(title, description, stock, language, productType, price, rating, url, genre, releaseDate, available);
		this.isbn = isbn;
		this.author = author;
		this.publisher = publisher;
		this.pageNumber = pageNumber;
	}

	
	
	
	
	
}
