package com.castelao.mediaflix_v4.dto;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.castelao.mediaflix_v4.entities.ProductType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

	private Long productId;

	@NotEmpty
	private String title;

	@NotEmpty
	private String description;

	@NotNull
	private Integer stock;

	private String language;

	@NotEmpty
	private ProductType productType;

	@NotNull
	private Double price;

	private Double rating;

	@NotEmpty
	private String url;

	@NotEmpty
	private String genre;

	@NotNull
	private Date releaseDate;

	@NotNull
	private Boolean available;

	public ProductDto(String title, String description, Integer stock, String idioma, ProductType productType,
			Double price, Double rating, String url, String genre, Date releaseDate, Boolean available) {
		this.title = title;
		this.description = description;
		this.stock = stock;
		this.language = idioma;
		this.productType = productType;
		this.price = price;
		this.rating = rating;
		this.url = url;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.available = available;
	}

	public ProductDto(Long productId, @NotEmpty String title, @NotEmpty String description, @NotNull Integer stock,
			String language, @NotEmpty ProductType productType, @NotNull Double price, Double rating,
			@NotEmpty String url, @NotEmpty String genre, @NotNull Date releaseDate, @NotNull Boolean available) {
		this.productId = productId;
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
	}

	

}
