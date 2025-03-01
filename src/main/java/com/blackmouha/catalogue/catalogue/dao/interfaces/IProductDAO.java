package com.blackmouha.catalogue.catalogue.dao.interfaces;

import com.blackmouha.catalogue.catalogue.entities.Product;

import java.util.List;

public interface IProductDAO {
    public List<Product> findAll();
    public Product findByReference(String reference);
    public void save(Product product);
    public void update(Product product);
    public void delete(String reference);
    public List<Product> findByName(String designation);
}
