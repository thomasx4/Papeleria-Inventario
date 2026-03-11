package com.inventario.papeleria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventario.papeleria.dto.CategoriesRequestDTO;
import com.inventario.papeleria.dto.CategoriesResponseDTO;
import com.inventario.papeleria.service.CategoriesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")

public class CategoriesController {

    private final CategoriesService categoriesService;
    
    //Create categories

    @PostMapping
    public ResponseEntity <CategoriesResponseDTO> createCategoriy(@RequestBody CategoriesRequestDTO categoriesRequestDTO){
        try {
            CategoriesResponseDTO response = categoriesService.createCategories(categoriesRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    //Get categories

    @GetMapping
    public ResponseEntity <List<CategoriesResponseDTO>> getCategories(){
        try {
            List<CategoriesResponseDTO> response = categoriesService.getCategories();
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Get By ID categories

    @GetMapping("/{id}")
    public ResponseEntity <Optional<CategoriesResponseDTO>> getCategoryById(@PathVariable Long id){
        try {
            Optional<CategoriesResponseDTO> response = categoriesService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    //Get By Name categories

    @GetMapping("/name/{name}")
        public ResponseEntity <Optional<CategoriesResponseDTO>> getCategoryByName(@PathVariable String name){
        try {
            Optional<CategoriesResponseDTO> response = categoriesService.getCategoryByName(name);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Update categories

    @PutMapping("/{id}")
    public ResponseEntity <CategoriesResponseDTO> updateCategory (@PathVariable Long id, @RequestBody CategoriesRequestDTO categoriesRequestDTO){
        try {
            CategoriesResponseDTO response = categoriesService.updateCategory(id, categoriesRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Update Specific Category

    @PatchMapping("/{id}")
    public ResponseEntity <CategoriesResponseDTO> updateSpecificCategory(@PathVariable Long id, @RequestBody CategoriesRequestDTO categoriesRequestDTO){
        try {
            CategoriesResponseDTO response = categoriesService.updateCategory(id, categoriesRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Delete categories

    @DeleteMapping("/{id}")
    public ResponseEntity <CategoriesResponseDTO> delete(@PathVariable Long id){
        try {
            categoriesService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}   
