package com.inventario.papeleria.dto;

import lombok.Data;

@Data
public class UsersRequestDTO {

    private String name;
    private String email;
    private String password;
    private String role;

}
