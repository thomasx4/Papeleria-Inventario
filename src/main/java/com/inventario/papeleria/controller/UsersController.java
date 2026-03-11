package com.inventario.papeleria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventario.papeleria.dto.UsersRequestDTO;
import com.inventario.papeleria.dto.UsersResponseDTO;
import com.inventario.papeleria.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    // Crear usuario
    @PostMapping
    public ResponseEntity<UsersResponseDTO> createUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        try {

            UsersResponseDTO response = usersService.createUser(usersRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> getUsers() {

        try {

            List<UsersResponseDTO> response = usersService.getUsers();

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Modificar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UsersRequestDTO usersRequestDTO) {

        try {

            UsersResponseDTO response = usersService.updateUser(id, usersRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        try {

            usersService.deleteUser(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    // Buscar usuario por Nombre
    @GetMapping("/search")
    public ResponseEntity<List<UsersResponseDTO>> searchUsers(@RequestParam String name){

        return ResponseEntity.ok(usersService.getUsersByName(name));
    }

    // Buscar usuario por email
    @GetMapping("/email")
    public ResponseEntity<UsersResponseDTO> getUserByEmail(@RequestParam String email){

        return ResponseEntity.ok(usersService.getUserByEmail(email));
    }

    // Buscar por rol
    @GetMapping("/role")
    public ResponseEntity<List<UsersResponseDTO>> getUsersByRole(@RequestParam String role){

        return ResponseEntity.ok(usersService.getUsersByRole(role));
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserById(@PathVariable Long id) {

        try {

            UsersResponseDTO response = usersService.getUserById(id);

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
