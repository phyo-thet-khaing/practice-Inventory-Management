package com.ptk.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptk.demo.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findByHash(String fileHash);

}