package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}