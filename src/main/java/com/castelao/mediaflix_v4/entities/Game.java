package com.castelao.mediaflix_v4.entities;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game extends Product {

	@NotNull
	private String developer;

	private String platform;

	private Integer duration;

	private String urlTrailer;

	public Game(Long productId, @NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull String developer,
			String platform, Integer duration, String urlTrailer) {
		super(productId, title, description, stock, language, productType, price, rating, url, genre, releaseDate,
				available);
		this.developer = developer;
		this.platform = platform;
		this.duration = duration;
		this.urlTrailer = urlTrailer;
	}

	public Game(@NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull String developer,
			String platform, Integer duration, String urlTrailer) {
		super(title, description, stock, language, productType, price, rating, url, genre, releaseDate, available);
		this.developer = developer;
		this.platform = platform;
		this.duration = duration;
		this.urlTrailer = urlTrailer;
	}

}
