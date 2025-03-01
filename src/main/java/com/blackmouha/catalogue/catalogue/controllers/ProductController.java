package com.blackmouha.catalogue.catalogue.controllers;

import com.blackmouha.catalogue.catalogue.dto.ProductDTO;
import com.blackmouha.catalogue.catalogue.entities.Product;
import com.blackmouha.catalogue.catalogue.mapper.ProductMapper;
import com.blackmouha.catalogue.catalogue.metier.interfaces.IProductMetier;
import com.blackmouha.catalogue.catalogue.utils.FormValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductMetier productMetier;

    private final ProductMapper productMapper = new ProductMapper();

    @GetMapping
    @ResponseBody
    public List<ProductDTO> index() {
        return productMetier.getProducts();
    }

    @GetMapping("/{reference}")
    @ResponseBody
    public ProductDTO getProduct(@PathVariable String reference) {
        return productMetier.getProduct(reference);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<FormValidations> create(@RequestBody ProductDTO product) {
        try {
            System.out.println("Creating product: " + product);
            FormValidations result = productMetier.addProduct(product);
            if (result.toString().isEmpty()) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

        } catch (Exception e) {
            System.out.println("Quelque chose s'est mal passé !");
            System.out.println(e.getMessage());
            ResponseEntity.internalServerError().body("Quelque chose s'est mal passé. Veuillze réessayer plus tard !");
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{reference}")
    @ResponseBody
    public ResponseEntity<FormValidations> update(@RequestBody ProductDTO product) {
        FormValidations result = productMetier.updateProduct(product);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{reference}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable String reference) {
        String result = productMetier.deleteProduct(reference);
        return ResponseEntity.ok(result);
    }

    public ProductMapper getProductMapper() {
        return productMapper;
    }
}
