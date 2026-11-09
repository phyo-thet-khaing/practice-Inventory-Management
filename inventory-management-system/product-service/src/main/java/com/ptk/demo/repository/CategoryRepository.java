package com.ptk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptk.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
