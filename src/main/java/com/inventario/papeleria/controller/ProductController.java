package com.inventario.papeleria.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventario.papeleria.dto.ProductRequestDTO;
import com.inventario.papeleria.dto.ProductResponseDTO;
import com.inventario.papeleria.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productsService;

    // Crear producto
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductRequestDTO dto){

        return ResponseEntity.ok(productsService.createProduct(dto));
    }

    // Obtener productos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts(){

        return ResponseEntity.ok(productsService.getProducts());
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto){

        return ResponseEntity.ok(productsService.updateProduct(id, dto));
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        productsService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}
