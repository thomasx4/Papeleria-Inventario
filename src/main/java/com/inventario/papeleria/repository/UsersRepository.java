package com.inventario.papeleria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.papeleria.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{


Optional<User> findById(Long id);

List<User> findByNameContaining(String name);

Optional<User> findByEmail(String email);

List<User> findByRole(String role);

}