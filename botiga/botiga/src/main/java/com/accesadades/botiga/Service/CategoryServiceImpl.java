package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Category;
import com.accesadades.botiga.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service // Marca esta clase como un componente de servicio gestionado por Spring
public class CategoryServiceImpl implements CategoryService{
    @Autowired // Inyección de dependencia de CategoryRepository
    private CategoryRepository categoryRepository;
    // Método para encontrar todas las categorías
    @Override
    public Set<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
    // Método para encontrar una categoría por su nombre
    @Override
    public Category findCategorybyName(String name) {
        return categoryRepository.findByName(name);
    }
    // Método para crear una nueva categoría
    @Override
    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }
    // Método para eliminar una categoría por su identificador
    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    // Método para encontrar una categoría por su identificador
    @Override
    public Category findById(long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
