package com.blackmouha.catalogue.catalogue.dto;

import com.blackmouha.catalogue.catalogue.entities.Product;

public class ProductDTO {
    private String reference;
    private String designation;
    private Double price;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(String reference, String designation, Double price, Integer quantity) {
        this.reference = reference;
        this.designation = designation;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDTO(Product product) {
        this.reference = product.getReference();
        this.designation = product.getDesignation();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
