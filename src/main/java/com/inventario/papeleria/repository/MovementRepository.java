package com.inventario.papeleria.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.papeleria.entity.Movement;
@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByType(String type);

    List<Movement> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Movement> findByProductId(Long productId);
}