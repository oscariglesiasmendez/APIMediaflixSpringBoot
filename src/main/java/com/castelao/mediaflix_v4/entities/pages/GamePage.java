package com.castelao.mediaflix_v4.entities.pages;

import java.util.List;

import com.castelao.mediaflix_v4.entities.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePage extends Page<Game> {

	private List<Game> games;
	private int total;
	private int skip;
	private int limit;
	
}