package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Category;
import com.accesadades.botiga.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Set<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findCategorybyName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category findById(long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
