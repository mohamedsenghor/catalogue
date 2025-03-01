package com.blackmouha.catalogue.catalogue.mapper;

import com.blackmouha.catalogue.catalogue.dto.ProductDTO;
import com.blackmouha.catalogue.catalogue.entities.Product;

public class ProductMapper {
    public Product toEntity (ProductDTO productDTO) {
        return new Product(productDTO);
    }
    public ProductDTO toDTO (Product product) {
        return new ProductDTO(product);
    }
}
