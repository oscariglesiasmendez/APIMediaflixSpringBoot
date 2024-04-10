package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@NotNull
	private String title;

	@Column(length = 10000)
	private String description;

	@NotNull
	private Integer stock;

	private String language;

	@NotNull
	@Enumerated(value = EnumType.STRING)
	@Column(name = "product_type")
	private ProductType productType;

	@NotNull
	private Double price;

	private Double rating;

	@NotNull
	// Image url (Cloudinary)
	private String url;

	private String genre;

	@NotNull
	@Column(name = "release_date")
	private Date releaseDate;

	@NotNull
	@Column(columnDefinition = "TINYINT")
	private Boolean available;

	public Product(Long productId, @NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available) {
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

	public Product(@NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available) {
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
