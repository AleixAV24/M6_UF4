package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Subcategory;
import com.accesadades.botiga.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service // Marca esta clase como un componente de servicio gestionado por Spring
public class SubcategoryImpl implements SubCategoryService{
    @Autowired // Inyección de dependencia de SubCategoryRepository
    private SubCategoryRepository subCategoryRepository;
    @Override // Método para encontrar todas las subcategorías
    public Set<Subcategory> findAll() {
        return subCategoryRepository.findAll();
    }
    // Método para encontrar una subcategoría por su nombre
    @Override
    public Subcategory findByName(String name) {
        return subCategoryRepository.findByName(name);
    }
    // Método para crear una nueva subcategoría
    @Override
    public Subcategory createSubCategory(Subcategory subcategory) {
        subCategoryRepository.save(subcategory);
        return subcategory;
    }
    // Método para eliminar una subcategoría por su identificador
    @Override
    public void deleteSubcategory(Long subcategory_id) {
        subCategoryRepository.deleteById(subcategory_id);
    }
    // Método para encontrar una subcategoría por su identificador
    @Override
    public Subcategory findById(long subcategory_id) {
        return subCategoryRepository.findById(subcategory_id).orElse(null);
    }
}
