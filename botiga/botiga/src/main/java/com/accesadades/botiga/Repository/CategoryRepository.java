package com.accesadades.botiga.Repository;

import java.util.Set;
import com.accesadades.botiga.Model.Category;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // Esta interfaz es un componente de repositorio gestionado por Spring
public interface CategoryRepository extends CrudRepository<Category, Long> {
    // Este método encuentra todas las categorías y devuelve un conjunto de ellas
    @Override
    @NonNull
    Set<Category> findAll();

    // Este método encuentra una categoría por su nombre
    Category findByName(String name);

    // Este método guarda una nueva categoría en la base de datos
    @SuppressWarnings({ "null", "unchecked" })
    Category save(Category category);

    // Este método elimina una categoría por su identificador
    @SuppressWarnings("null")
    void deleteById(Long category_id);
}
