package com.inventario.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.papeleria.entity.Movement;
@Repository
public interface MovementRepository extends JpaRepository <Movement, Long>{

    
}