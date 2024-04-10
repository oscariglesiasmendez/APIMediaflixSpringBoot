package com.castelao.mediaflix_v4.dto.pages;

import java.util.List;

import com.castelao.mediaflix_v4.dto.MovieDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePageDto {
    
	private List<MovieDto> movies;
    private long total;
    private int skip;
    private int limit;

    
}