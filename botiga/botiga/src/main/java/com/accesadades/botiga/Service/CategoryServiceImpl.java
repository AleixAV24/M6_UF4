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
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
