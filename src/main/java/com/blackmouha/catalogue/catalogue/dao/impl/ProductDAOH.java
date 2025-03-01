package com.blackmouha.catalogue.catalogue.dao.impl;

import com.blackmouha.catalogue.catalogue.dao.interfaces.IProductDAO;
import com.blackmouha.catalogue.catalogue.entities.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("hashmap")
public class ProductDAOH implements IProductDAO {
    private Map<String, Product> products = new HashMap<>();

    /**
     * @return
     */
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    /**
     * @param reference
     * @return
     */
    @Override
    public Product findByReference(String reference) {
        return products.get(reference);
    }

    /**
     * @param product
     * @return
     */
    @Override
    public void save(Product product) {
        products.put(product.getReference(), product);
    }

    /**
     * @param product
     * @return
     */
    @Override
    public void update(Product product) {
        products.put(product.getReference(), product);
    }

    /**
     * @param reference
     */
    @Override
    public void delete(String reference) {
        products.remove(reference);
    }

    /**
     * @param designation
     * @return
     */
    @Override
    public List<Product> findByName(String designation) {
        return products.values().stream().filter(product -> product.getDesignation().contains(designation)).toList();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }
}
