package com.accesadades.botiga.Repository;

import com.accesadades.botiga.Model.Subcategory;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository // Esta interfaz es un componente de repositorio gestionado por Spring
public interface SubCategoryRepository extends CrudRepository<Subcategory, Long> {

    // Este método encuentra todas las subcategorías y devuelve un conjunto de ellas
    @Override
    @NonNull
    Set<Subcategory> findAll();

    // Este método encuentra una subcategoría por su nombre
    Subcategory findByName(String name);

    // Este método guarda una nueva subcategoría en la base de datos
    @SuppressWarnings({ "unchecked", "null" })
    Subcategory save(Subcategory subcategory);

    // Este método elimina una subcategoría por su identificador
    @SuppressWarnings("null")
    void deleteById(Long subcategory_id);
}
