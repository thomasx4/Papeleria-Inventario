package com.inventario.papeleria.dto;

import lombok.Data;

@Data
public class MovementRequestDTO {
    private String type;
    private int amount;
    private String description;
    private Long productId;
    private Long userId;
}