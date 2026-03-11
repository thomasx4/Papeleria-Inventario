package com.inventario.papeleria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.papeleria.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository <Categories, Long> {
Optional<Categories> findByName(String name);
    
}