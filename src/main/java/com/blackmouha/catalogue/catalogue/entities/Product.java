package com.blackmouha.catalogue.catalogue.entities;

import com.blackmouha.catalogue.catalogue.dto.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "reference", length = 25)
    private String reference;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Product() {}

    public Product(ProductDTO productDTO) {
        this.reference = productDTO.getReference();
        this.designation = productDTO.getDesignation();
        this.price = productDTO.getPrice();
        this.quantity = productDTO.getQuantity();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = String.valueOf(reference);
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

    @Override
    public String toString() {
        return "Product{" +
            "reference='" + reference + '\'' +
            ", designation='" + designation + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            '}';
    }

}
