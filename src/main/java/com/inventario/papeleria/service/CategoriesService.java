package com.inventario.papeleria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventario.papeleria.dto.CategoriesRequestDTO;
import com.inventario.papeleria.dto.CategoriesResponseDTO;
import com.inventario.papeleria.entity.Categories;
import com.inventario.papeleria.repository.CategoriesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;


//----------------------------------------------------------------------------------------------
    //Create categories

    public CategoriesResponseDTO createCategories(CategoriesRequestDTO categoriesRequestDTO){
        Categories categories = new Categories();
        categories.setName(categoriesRequestDTO.getName());
        categories.setDescription(categoriesRequestDTO.getDescription());

        categoriesRepository.save(categories);
        CategoriesResponseDTO response = new CategoriesResponseDTO();
        response.setId((categories.getId()));
        response.setName(categories.getName());
        response.setDescription(categories.getDescription());

        return response;
    }


//----------------------------------------------------------------------------------------------
    //Get categories


    public List<CategoriesResponseDTO> getCategories(){
        List<Categories> categories = categoriesRepository.findAll();
        List<CategoriesResponseDTO> listCategories = new ArrayList<>();

        for (Categories categorie: categories){
            CategoriesResponseDTO categoriesResponseDTO = new CategoriesResponseDTO();
            categoriesResponseDTO.setId(categorie.getId());
            categoriesResponseDTO.setName(categorie.getName());
            categoriesResponseDTO.setDescription(categorie.getDescription());

            listCategories.add(categoriesResponseDTO);
        }
        return listCategories;

    }


//----------------------------------------------------------------------------------------------
    //Get By ID categories

    public Optional<CategoriesResponseDTO> getCategoryById(Long id){
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Categories category = optionalCategory.get();
            CategoriesResponseDTO response = new CategoriesResponseDTO();

            // mapeop de datos de la entidad al DTO

            response.setId(category.getId());
            response.setName(category.getName());
            response.setDescription(category.getDescription());

            return Optional.of(response);
        } else{

            System.out.println("Cateogira no encontrada");
            return Optional.empty();

        }
    }


//----------------------------------------------------------------------------------------------
    //Get By Name categories


        public Optional<CategoriesResponseDTO> getCategoryByName(String name){
        Optional<Categories> optionalCategory = categoriesRepository.findByName(name);

        if (optionalCategory.isPresent()) {
            Categories category = optionalCategory.get();
            CategoriesResponseDTO response = new CategoriesResponseDTO();

            // mapeop de datos de la entidad al DTO

            response.setId(category.getId());
            response.setName(category.getName());
            response.setDescription(category.getDescription());

            return Optional.of(response);
        } else{

            return Optional.empty();

        }
    }


//----------------------------------------------------------------------------------------------
    //Update categories

    public CategoriesResponseDTO updateCategory(Long id, CategoriesRequestDTO categoriesRequestDTO){
        Categories categories = categoriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        
        if (categoriesRequestDTO.getName() != null) {
            categories.setName(categoriesRequestDTO.getName());            
        }
        if (categoriesRequestDTO.getDescription() != null) {
            categories.setDescription(categoriesRequestDTO.getDescription());
        }
        
        categoriesRepository.save(categories);
        CategoriesResponseDTO response = new CategoriesResponseDTO();
        response.setId(categories.getId());
        response.setName(categories.getName());
        response.setDescription(categories.getDescription());

        return response;
    }

    
//----------------------------------------------------------------------------------------------
    //Delete categories

    public void deleteCategory (Long id){
        Categories categories = categoriesRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        categoriesRepository.delete(categories);


        
    }


}
