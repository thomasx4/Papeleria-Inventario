package com.inventario.papeleria.dto;

import lombok.Data;

@Data
public class ProductRequestDTO {

    private String name;

    private String description;

    private Double price;

    private Long stocks;

    private Long categoriesId;

}
