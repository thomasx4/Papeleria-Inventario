package com.inventario.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.papeleria.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository <Categories, Long> {

    
}