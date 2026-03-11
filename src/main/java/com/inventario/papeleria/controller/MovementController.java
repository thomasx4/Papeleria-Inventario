package com.inventario.papeleria.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.papeleria.dto.MovementRequestDTO;
import com.inventario.papeleria.dto.MovementResponseDTO;
import com.inventario.papeleria.service.MovementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movements")
public class MovementController {

    private final MovementService movementService;

//----------------------------------------------------------------------------------------------
    // Create movement



    @PostMapping
    public ResponseEntity<MovementResponseDTO> create(@RequestBody MovementRequestDTO movementRequestDTO) {

        try {
            MovementResponseDTO response = movementService.createMovement(movementRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//----------------------------------------------------------------------------------------------
    // Get movement



    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> getMovement() {
        try {
            List<MovementResponseDTO> response = movementService.getMovements();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//----------------------------------------------------------------------------------------------
    // Get movement by Type



    @GetMapping("/{id}")
    public ResponseEntity<Optional<MovementResponseDTO>> getById(@PathVariable Long id) {
        try {
            Optional<MovementResponseDTO> response = movementService.getMovementById(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//----------------------------------------------------------------------------------------------
    // Get movement By Date


    @GetMapping("/search-date")
    public ResponseEntity<List<MovementResponseDTO>> getByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        try {
            List<MovementResponseDTO> response = movementService.getMovementsByDateRange(start, end);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

 //----------------------------------------------------------------------------------------------
    // Get movement by Type



    @GetMapping("/type/{type}")
    public ResponseEntity<List<MovementResponseDTO>> getByType(@PathVariable String type) {

        try {
            List<MovementResponseDTO> response = movementService.getMovementsByType(type);
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//----------------------------------------------------------------------------------------------
    // Get movement by Product ID



    @GetMapping("/product/{productId}")
    public ResponseEntity<List<MovementResponseDTO>> getByProductId(@PathVariable Long productId) {
       try {
           List<MovementResponseDTO> response = movementService.getMovementsByProductId(productId);
           return ResponseEntity.status(HttpStatus.FOUND).body(response);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
    }

//----------------------------------------------------------------------------------------------
    // Update movement



    @PutMapping("/{id}")
    public ResponseEntity<MovementResponseDTO> update(@PathVariable Long id, @RequestBody MovementRequestDTO movementRequestDTO) {
        try {
            MovementResponseDTO response = movementService.updateMovement(id, movementRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//----------------------------------------------------------------------------------------------
    // Update Specific Category



    @PatchMapping("/{id}")
    public ResponseEntity<MovementResponseDTO> updateSpecificMovement(
        @PathVariable Long id, 
        @RequestBody MovementRequestDTO dto) {
     try {
         MovementResponseDTO response = movementService.updateMovement(id, dto);
         return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

//----------------------------------------------------------------------------------------------
    // Delete movement



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            movementService.deleteMovement(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}