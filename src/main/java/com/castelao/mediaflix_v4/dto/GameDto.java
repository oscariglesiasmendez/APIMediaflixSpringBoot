package com.castelao.mediaflix_v4.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.castelao.mediaflix_v4.entities.ProductType;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameDto {

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
	private String developer;
	private String platform;
	private Integer duration; // En horas
	private String urlTrailer;

	
	public GameDto(Long gameId, String title, String description, Integer stock, String language, ProductType productType,
			Double price, Double rating, String url, String genre, Date releaseDate, Boolean available,
			String developer, String platform, Integer duration, String urlTrailer) {
		this.productId = gameId;
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
		this.developer = developer;
		this.platform = platform;
		this.duration = duration;
		this.urlTrailer = urlTrailer;
	}
	
//	public GameDto(String title, String description, Integer stock, String language, ProductType productType,
//			BigDecimal price, BigDecimal rating, String url, String genre, Date releaseDate, Boolean available,
//			String developer, String platform, Integer duration, String urlTrailer) {
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
//		this.developer = developer;
//		this.platform = platform;
//		this.duration = duration;
//		this.urlTrailer = urlTrailer;
//	}

}
