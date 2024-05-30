package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Subcategory;
import com.accesadades.botiga.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SubcategoryImpl implements SubCategoryService{
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public Set<Subcategory> findAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public Subcategory findByName(String name) {
        return subCategoryRepository.findByName(name);
    }

    @Override
    public Subcategory createSubCategory(Subcategory subcategory) {
        return subCategoryRepository.save(subcategory);
    }

    @Override
    public void deleteSubcategory(long subcategory_id) {
        subCategoryRepository.deleteById(subcategory_id);
    }
}