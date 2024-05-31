package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Subcategory;

import java.util.Set;

public interface SubCategoryService {
    // Método para encontrar todas las subcategorías
    Set<Subcategory> findAll();
    // Método para encontrar una subcategoría por su nombre
    Subcategory findByName(String name);
    // Método para crear una nueva subcategoría
    Subcategory createSubCategory(Subcategory subcategory);
    // Método para eliminar una subcategoría por su identificador
    void deleteSubcategory(Long subcategory_id);
    // Método para encontrar una subcategoría por su identificador
    Subcategory findById(long subcategory_id);
}
