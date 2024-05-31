package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Subcategory;

import java.util.Set;

public interface SubCategoryService {
    Set<Subcategory> findAll();
    Subcategory findByName(String name);
    Subcategory createSubCategory(Subcategory subcategory);
    void deleteSubcategory(Long subcategory_id);
    Subcategory findById(long subcategory_id);
}
