package com.blackmouha.catalogue.catalogue.metier.interfaces;

import com.blackmouha.catalogue.catalogue.dto.ProductDTO;
import com.blackmouha.catalogue.catalogue.utils.FormValidations;

import java.util.List;

public interface IProductMetier {
    public List<ProductDTO> getProducts();
    public ProductDTO getProduct(String reference);
    public FormValidations addProduct(ProductDTO product);
    public FormValidations updateProduct(ProductDTO product);
    public String deleteProduct(String reference);
    public List<ProductDTO> getProductsByDesignation(String designation);
}
