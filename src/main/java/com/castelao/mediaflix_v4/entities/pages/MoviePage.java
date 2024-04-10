package com.castelao.mediaflix_v4.entities.pages;

import java.util.List;

import com.castelao.mediaflix_v4.entities.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePage extends Page<Movie> {
	
	private List<Movie> movies;
	private int total;
	private int skip;
	private int limit;
	
}