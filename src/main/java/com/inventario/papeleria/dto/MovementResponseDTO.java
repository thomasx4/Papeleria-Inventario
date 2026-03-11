package com.inventario.papeleria.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MovementResponseDTO {
    private Long id;
    private String type;
    private int amount;
    private LocalDateTime date;
    private String description;
    private Long productId;
    private String productName;
    private String userName;
}