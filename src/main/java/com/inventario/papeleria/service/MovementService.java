package com.inventario.papeleria.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.MovementRequestDTO;
import com.inventario.papeleria.dto.MovementResponseDTO;
import com.inventario.papeleria.entity.Movement;
import com.inventario.papeleria.repository.MovementRepository;
import com.inventario.papeleria.repository.ProductRepository;
import com.inventario.papeleria.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService {
    private final MovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final UsersRepository userRepository;


//----------------------------------------------------------------------------------------------
    // Create movement

    public MovementResponseDTO createMovement(MovementRequestDTO dto) {
        Movement movement = new Movement();
        movement.setType(dto.getType());
        movement.setAmount(dto.getAmount());
        movement.setDescription(dto.getDescription());
        movement.setDate(LocalDateTime.now());

        movement.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        
        movement.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        movementRepository.save(movement);

        MovementResponseDTO response = new MovementResponseDTO();
        response.setId(movement.getId());
        response.setType(movement.getType());
        response.setAmount(movement.getAmount());
        response.setDate(movement.getDate());
        response.setDescription(movement.getDescription());
        response.setProductId(movement.getProduct().getId());
        response.setProductName(movement.getProduct().getName());
        response.setUserName(movement.getUser().getName());

        return response;
    }


//----------------------------------------------------------------------------------------------    
    // Get movement

    public List<MovementResponseDTO> getMovements() {
        List<Movement> movements = movementRepository.findAll();
        List<MovementResponseDTO> listResponse = new ArrayList<>();

        for (Movement movement : movements) {
            MovementResponseDTO response = new MovementResponseDTO();
            response.setId(movement.getId());
            response.setType(movement.getType());
            response.setAmount(movement.getAmount());
            response.setDate(movement.getDate());
            response.setDescription(movement.getDescription());
            response.setProductId(movement.getProduct().getId());
            response.setProductName(movement.getProduct().getName());
            response.setUserName(movement.getUser().getName());
            listResponse.add(response);
        }
        return listResponse;
    }


//----------------------------------------------------------------------------------------------
    // Get movement by Type

    public List<MovementResponseDTO> getMovementsByType(String type) {
        List<Movement> movements = movementRepository.findByType(type);
        List<MovementResponseDTO> list = new ArrayList<>();
        for (Movement movement : movements) {
            MovementResponseDTO response = new MovementResponseDTO();
            response.setId(movement.getId());
            response.setType(movement.getType());
            response.setAmount(movement.getAmount());
            response.setDate(movement.getDate());
            response.setDescription(movement.getDescription());
            response.setProductId(movement.getProduct().getId());
            response.setProductName(movement.getProduct().getName());
            response.setUserName(movement.getUser().getName());
            list.add(response);
        }
        return list;
    }


//----------------------------------------------------------------------------------------------
    // Get movement By Date

    public List<MovementResponseDTO> getMovementsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<Movement> movements = movementRepository.findByDateBetween(start, end);
        List<MovementResponseDTO> list = new ArrayList<>();
        for (Movement movement : movements) {
            MovementResponseDTO response = new MovementResponseDTO();
            response.setId(movement.getId());
            response.setType(movement.getType());
            response.setAmount(movement.getAmount());
            response.setDate(movement.getDate());
            response.setDescription(movement.getDescription());
            response.setProductId(movement.getProduct().getId());
            response.setProductName(movement.getProduct().getName());
            response.setUserName(movement.getUser().getName());
            list.add(response);
        }
        return list;
    }   


//----------------------------------------------------------------------------------------------    
    // Get movement by Product ID

    public List<MovementResponseDTO> getMovementsByProductId(Long productId) {
    List<Movement> movements = movementRepository.findByProductId(productId);
    List<MovementResponseDTO> listResponse = new ArrayList<>();

    for (Movement movement : movements) {
        MovementResponseDTO response = new MovementResponseDTO();
        response.setId(movement.getId());
        response.setType(movement.getType());
        response.setAmount(movement.getAmount());
        response.setDate(movement.getDate());
        response.setDescription(movement.getDescription());
        response.setProductId(movement.getProduct().getId());
        response.setProductName(movement.getProduct().getName());
        response.setUserName(movement.getUser().getName());

        listResponse.add(response);
    }
    return listResponse;
}


//----------------------------------------------------------------------------------------------
    // Get movement by ID

    public Optional<MovementResponseDTO> getMovementById(Long id) {
        Optional<Movement> optional = movementRepository.findById(id);

        if (optional.isPresent()) {
            Movement movement = optional.get();
            MovementResponseDTO response = new MovementResponseDTO();
            response.setId(movement.getId());
            response.setType(movement.getType());
            response.setAmount(movement.getAmount());
            response.setDate(movement.getDate());
            response.setDescription(movement.getDescription());
            response.setProductId(movement.getProduct().getId());
            response.setProductName(movement.getProduct().getName());
            response.setUserName(movement.getUser().getName());
            return Optional.of(response);
        }
        return Optional.empty();
    }


//----------------------------------------------------------------------------------------------
    // Update movement

    public MovementResponseDTO updateMovement(Long id, MovementRequestDTO dto) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        if (dto.getType() != null) movement.setType(dto.getType());
        if (dto.getAmount() != 0) movement.setAmount(dto.getAmount());
        if (dto.getDescription() != null) movement.setDescription(dto.getDescription());
        
        if (dto.getProductId() != null) {
            movement.setProduct(productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
        }

        movementRepository.save(movement);

        MovementResponseDTO response = new MovementResponseDTO();
        response.setId(movement.getId());
        response.setType(movement.getType());
        response.setAmount(movement.getAmount());
        response.setDate(movement.getDate());
        response.setDescription(movement.getDescription());
        response.setProductId(movement.getProduct().getId());
        response.setProductName(movement.getProduct().getName());
        response.setUserName(movement.getUser().getName());

        return response;
    }


//----------------------------------------------------------------------------------------------
    // Delete movement

    public void deleteMovement(Long id) {
        Movement movement = movementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        movementRepository.delete(movement);
        
    }


}
