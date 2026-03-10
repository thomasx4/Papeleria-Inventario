package com.inventario.papeleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.UsersRequestDTO;
import com.inventario.papeleria.dto.UsersResponseDTO;
import com.inventario.papeleria.entity.User;
import com.inventario.papeleria.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    // Crear usuario
    public UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO) {

        User user = new User();

        user.setName(usersRequestDTO.getName());
        user.setEmail(usersRequestDTO.getEmail());
        user.setPassword(usersRequestDTO.getPassword());
        user.setRole(usersRequestDTO.getRole());

        User savedUser = usersRepository.save(user);

        UsersResponseDTO response = new UsersResponseDTO();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());

        return response;
    }

    // Obtener todos los usuarios
    public List<UsersResponseDTO> getUsers() {

        List<User> users = usersRepository.findAll();

        return users.stream().map(user -> {

            UsersResponseDTO response = new UsersResponseDTO();

            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());

            return response;

        }).collect(Collectors.toList());
    }

    // Modificar usuario
    public UsersResponseDTO updateUser(Long id, UsersRequestDTO usersRequestDTO) {

        User user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(usersRequestDTO.getName());
        user.setEmail(usersRequestDTO.getEmail());
        user.setPassword(usersRequestDTO.getPassword());
        user.setRole(usersRequestDTO.getRole());

        User updatedUser = usersRepository.save(user);

        UsersResponseDTO response = new UsersResponseDTO();

        response.setId(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setEmail(updatedUser.getEmail());
        response.setRole(updatedUser.getRole());

        return response;
    }

    // Eliminar usuario
    public void deleteUser(Long id) {

        User user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usersRepository.delete(user);
    }
}

