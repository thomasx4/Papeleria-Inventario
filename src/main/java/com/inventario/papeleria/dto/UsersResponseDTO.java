package com.inventario.papeleria.dto;

import lombok.Data;

@Data
public class UsersResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;

}
