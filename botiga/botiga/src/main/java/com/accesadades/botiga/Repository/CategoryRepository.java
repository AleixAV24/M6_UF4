package com.accesadades.botiga.Repository;

import java.util.Set;
import com.accesadades.botiga.Model.Category;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Override
    @NonNull
    Set<Category> findAll();
    Category findByName(String name);
}
