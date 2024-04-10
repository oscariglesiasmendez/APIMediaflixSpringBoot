package com.castelao.mediaflix_v4.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelao.mediaflix_v4.dto.ProductDto;
import com.castelao.mediaflix_v4.dto.pages.ProductPageDto;
import com.castelao.mediaflix_v4.entities.Product;
import org.springframework.data.domain.Page;


public class ProductMapper {

    public static List<ProductDto> toDto(List<Product> entities) {
        List<ProductDto> dtos = new ArrayList<>();
        for (Product entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static ProductDto toDto(Product entity) {
        ProductDto dto = new ProductDto(entity.getProductId(), entity.getTitle(), entity.getDescription(), entity.getStock(), entity.getLanguage(), entity.getProductType(), entity.getPrice(), entity.getRating(), entity.getUrl(), entity.getGenre(), entity.getReleaseDate(), entity.getAvailable());
        return dto;
    }

    public static Product toEntity(ProductDto dto) {
        Product entity = new Product(dto.getProductId(), dto.getTitle(), dto.getDescription(), dto.getStock(), dto.getLanguage(), dto.getProductType(), dto.getPrice(), dto.getRating(), dto.getUrl(), dto.getGenre(), dto.getReleaseDate(), dto.getAvailable());
        return entity;
    }
    
    public static ProductPageDto toProductPageDto(Page<Product> page) {
        ProductPageDto productPageDto = new ProductPageDto();
        productPageDto.setProducts(toDto(page.getContent())); // getContent() para obtener la lista de productos
        productPageDto.setTotal(page.getTotalElements()); // getTotalElements() para obtener el total de elementos
        productPageDto.setSkip(page.getNumber() * page.getSize()); // getPageNumber() y getSize() para obtener el skip
        productPageDto.setLimit(page.getSize()); // getSize() para obtener el límite
        return productPageDto;
    }
    
}
