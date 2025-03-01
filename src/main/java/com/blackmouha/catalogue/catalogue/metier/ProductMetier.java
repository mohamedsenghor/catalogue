package com.blackmouha.catalogue.catalogue.metier;

import com.blackmouha.catalogue.catalogue.dao.interfaces.IProductDAO;
import com.blackmouha.catalogue.catalogue.dto.ProductDTO;
import com.blackmouha.catalogue.catalogue.entities.Product;
import com.blackmouha.catalogue.catalogue.mapper.ProductMapper;
import com.blackmouha.catalogue.catalogue.metier.interfaces.IProductMetier;
import com.blackmouha.catalogue.catalogue.utils.FormValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductMetier implements IProductMetier {
    @Autowired
    private IProductDAO productDAO;

    protected ProductMapper productMapper = new ProductMapper();

    public List<ProductDTO> getProducts() {
        return productDAO.findAll()
            .stream()
            .map(productMapper::toDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO getProduct(String reference) {
        if (reference == null) {
            return null;
        }
        return productMapper.toDTO(productDAO.findByReference(reference));
    }

    public FormValidations addProduct(ProductDTO product) {
        FormValidations result = verifyProduct(product);
        if (Objects.equals(result.toString(), "")) {
            productDAO.save(productMapper.toEntity(product));
        }
        return result;
    }

    public FormValidations updateProduct(ProductDTO product) {
        FormValidations result = verifyProduct(product);
        if (Objects.equals(result.toString(), "")) {
            productDAO.save(productMapper.toEntity(product));
        }
        return result;
    }

    public String deleteProduct(String reference) {
        if (reference == null) {
            return "{reference: 'No reference provided'}";
        }
        Product product = productDAO.findByReference(reference);
        if (product != null) {
            productDAO.delete(reference);
            return "Le produit " + product.getReference() + "a été supprimée avec succès !";
        }
        return "Ce produit n'existe pas !";
    }

    public List<ProductDTO> getProductsByDesignation(String designation) {
        if (designation == null || designation.length() < 3) {
            return null;
        }
        return productDAO.findByName(designation)
            .stream()
            .map(productMapper::toDTO)
            .collect(Collectors.toList());
    }

    public FormValidations verifyProduct(ProductDTO product) {
        FormValidations formValidations = new FormValidations();
        if (product == null) {
            formValidations.setReferenceError("No reference provided");
            return formValidations;
        }
        if (product.getReference() == null || product.getReference().length() < 3) {
            formValidations.setReferenceError("No reference provided");
        }
        if (product.getDesignation() == null || product.getDesignation().length() < 3) {
            formValidations.setDesignationError("No designation provided");
        }
        if (productDAO.findByReference(product.getReference()) != null) {
            formValidations.setReferenceError("Product already exists");
            return formValidations;
        }
        if (product.getQuantity() == null) {
            formValidations.setQuantityError("No quantity provided");
        } else {
            if (product.getQuantity() < 0) {
                formValidations.setQuantityError("Quantity must be a positive number");
            }
        }
        if (product.getDesignation() == null) {
            formValidations.setDesignationError("No designation provided");
        }
        if (product.getPrice() == null) {
            formValidations.setPriceError("No price provided");
        } else if (product.getPrice() <= 0) {
            formValidations.setPriceError("Price must be greater than 0");
        }
        return formValidations;
    }
}
