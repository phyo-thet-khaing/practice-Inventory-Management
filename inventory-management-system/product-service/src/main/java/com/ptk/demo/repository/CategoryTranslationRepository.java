package com.ptk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptk.demo.model.CategoryTranslation;

public interface CategoryTranslationRepository extends JpaRepository<CategoryTranslation, Long>{

}
