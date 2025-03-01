package com.blackmouha.catalogue.catalogue.dao.impl;

import com.blackmouha.catalogue.catalogue.dao.interfaces.IProductDAO;
import com.blackmouha.catalogue.catalogue.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("db")
public class ProductDAOD implements IProductDAO {
    @PersistenceContext
    private EntityManager em;

    /**
     * @return
     */
    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    /**
     * @param reference
     * @return
     */
    @Override
    public Product findByReference(String reference) {
        return em.find(Product.class, reference);
    }

    /**
     * @param product
     */
    @Override
    @Transactional
    public void save(Product product) {
        if (findByReference(product.getReference()) == null) {
            em.persist(product);
        } else em.merge(product);
    }

    /**
     * @param product
     */
    @Override
    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    /**
     * @param reference
     */
    @Override
    @Transactional
    public void delete(String reference) {
        Product product = findByReference(reference);
        if (product != null) {
            em.remove(product);
        }
    }

    /**
     * @param designation
     * @return
     */
    @Override
    public List<Product> findByName(String designation) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.designation LIKE designation", Product.class);
        return query.getResultList();
    }
}
