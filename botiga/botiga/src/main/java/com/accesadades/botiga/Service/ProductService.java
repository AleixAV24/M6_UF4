package com.accesadades.botiga.Service;

import java.util.Set;
import com.accesadades.botiga.Model.Product;

public interface ProductService {
    // Método para encontrar todos los productos
    Set<Product> findAllProducts();
    // Método para encontrar un producto por su nombre
    Product findProductsByName(String name);
    // Método para encontrar todos los productos asociados a una subcategoría
    Set<Product> findAllProducts(String subcategory);
    // Método para aumentar el precio de un producto
    void increasePrice(Product product);
    // Método para crear un nuevo producto
    Product createProduct(Product product);
    // Método para eliminar un producto por su identificador
    void deleteProductById(Long product_id);
}