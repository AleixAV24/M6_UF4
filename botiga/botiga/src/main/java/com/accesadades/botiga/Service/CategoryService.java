package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Category;

import java.util.Set;

public interface CategoryService {
    // Método para encontrar todas las categorías
    Set<Category> findAllCategory();
    // Método para encontrar una categoría por su nombre
    Category findCategorybyName(String name);
    // Método para crear una nueva categoría
    Category createCategory(Category category);
    // Método para eliminar una categoría por su identificador
    void deleteCategoryById(Long categoryId);
    // Método para encontrar una categoría por su identificador
    Category findById(long categoryId);
}
