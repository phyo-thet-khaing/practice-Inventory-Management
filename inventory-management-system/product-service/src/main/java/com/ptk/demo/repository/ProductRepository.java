package com.ptk.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ptk.demo.model.Product;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @Override
    @EntityGraph(attributePaths = "image")
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = "image")
    Optional<Product> findById(Long id);
    
    boolean existsByImage_Id(Long imageId);
}

