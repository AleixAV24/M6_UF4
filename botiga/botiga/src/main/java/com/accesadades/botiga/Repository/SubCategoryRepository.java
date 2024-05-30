package com.accesadades.botiga.Repository;

import com.accesadades.botiga.Model.Subcategory;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubCategoryRepository extends CrudRepository<Subcategory, Long> {
    @Override
    @NonNull
    Set<Subcategory> findAll();
    Subcategory findByName(String name);

}
