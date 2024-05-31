package com.accesadades.botiga.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
import com.accesadades.botiga.Model.Product;

@Repository // Esta interfaz es un componente de repositorio gestionado por Spring
public interface ProductRepository extends CrudRepository<Product, Long> {
    // Este método encuentra todos los productos y devuelve un conjunto de ellos
    @Override
    @NonNull
    Set<Product> findAll();

    // Este método encuentra un producto por su nombre
    Product findByName(String name);

    // Este método encuentra todos los productos asociados a una subcategoría por el nombre de la subcategoría
    Set<Product> findBySubcategoryName(String subcategoryName);

    // Este método encuentra productos por su nombre y su precio
    Set<Product> findByNameAndPrice(String name, float price);

    // Este método guarda un nuevo producto en la base de datos
    @SuppressWarnings({ "null", "unchecked" })
    Product save(Product product);

    // Este método elimina un producto por su identificador
    @SuppressWarnings("null")
    void deleteById(Long product_id);

}