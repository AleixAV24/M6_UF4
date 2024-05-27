package com.accesadades.botiga.Service;

import com.accesadades.botiga.Model.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> findAllCategory();
    Category findCategorybyName(String name);

}
