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
@Table(name = "movie")
public class Movie extends Product {

	@NotNull
	private String director;

	private Integer duration;

	private String studio;

	private String urlTrailer;

	public Movie(Long productId, @NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull String director,
			Integer duration, String studio, String urlTrailer) {
		super(productId, title, description, stock, language, productType, price, rating, url, genre, releaseDate,
				available);
		this.director = director;
		this.duration = duration;
		this.studio = studio;
		this.urlTrailer = urlTrailer;
	}

	public Movie(@NotNull String title, String description, @NotNull Integer stock, String language,
			@NotNull ProductType productType, @NotNull Double price, Double rating, @NotNull String url,
			String genre, @NotNull Date releaseDate, @NotNull Boolean available, @NotNull String director,
			Integer duration, String studio, String urlTrailer) {
		super(title, description, stock, language, productType, price, rating, url, genre, releaseDate, available);
		this.director = director;
		this.duration = duration;
		this.studio = studio;
		this.urlTrailer = urlTrailer;
	}

}
