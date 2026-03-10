package com.inventario.papeleria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.ProductRequestDTO;
import com.inventario.papeleria.dto.ProductResponseDTO;
import com.inventario.papeleria.entity.Product;
import com.inventario.papeleria.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // Crear producto
    public ProductResponseDTO createProduct(ProductRequestDTO dto){

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStocks(dto.getStocks());
        product.setCategoriesId(dto.getCategoriesId());

        Product saved = productRepository.save(product);

        ProductResponseDTO response = new ProductResponseDTO();

        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDescription(saved.getDescription());
        response.setPrice(saved.getPrice());
        response.setStocks(saved.getStocks());
        response.setCategoriesId(saved.getCategoriesId());

        return response;
    }

    // Obtener productos
    public List<ProductResponseDTO> getProducts(){

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> {

            ProductResponseDTO response = new ProductResponseDTO();

            response.setId(product.getId());
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setPrice(product.getPrice());
            response.setStocks(product.getStocks());
            response.setCategoriesId(product.getCategoriesId());

            return response;

        }).collect(Collectors.toList());
    }


// Actualizar producto
public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto){

    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    product.setName(dto.getName());
    product.setDescription(dto.getDescription());
    product.setPrice(dto.getPrice());
    product.setStocks(dto.getStocks());
    product.setCategoriesId(dto.getCategoriesId());

    Product updated = productRepository.save(product);

    ProductResponseDTO response = new ProductResponseDTO();

    response.setId(updated.getId());
    response.setName(updated.getName());
    response.setDescription(updated.getDescription());
    response.setPrice(updated.getPrice());
    response.setStocks(updated.getStocks());
    response.setCategoriesId(updated.getCategoriesId());

    return response;
    }

// Eliminar producto
public void deleteProduct(Long id){

    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

    productRepository.delete(product);
    }
}
