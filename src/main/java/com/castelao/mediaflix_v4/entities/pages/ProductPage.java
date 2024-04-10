package com.castelao.mediaflix_v4.entities.pages;

import java.util.List;

import com.castelao.mediaflix_v4.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPage {

	private List<Product> products;
	private int total;
	private int skip;
	private int limit;

}
