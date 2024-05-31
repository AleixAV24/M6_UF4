package com.accesadades.botiga.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;
import com.accesadades.botiga.Model.Product;
import com.accesadades.botiga.Repository.ProductRepository;

@Service // Marca esta clase como un componente de servicio gestionado por Spring
public class ProductServiceImpl implements ProductService {

    @Autowired // Inyección de dependencia de ProductRepository
    private ProductRepository productRepository;
    // Método para encontrar todos los productos
    @Override
    public Set<Product> findAllProducts() {
        return productRepository.findAll();
    }
    // Método para encontrar todos los productos asociados a una subcategoría
    @Override
    public Set<Product> findAllProducts(String subcategory) {
        return productRepository.findBySubcategoryName(subcategory);
    }
    // Método para encontrar un producto por su nombre
    @Override
    public Product findProductsByName(String name) {
        return productRepository.findByName(name);
    }
    // Método para aumentar el precio de un producto
    @Override
    public void increasePrice(Product product) { }
    // Método para crear un nuevo producto
    @Override
    public Product createProduct (Product product){
        productRepository.save(product);
        return product;
    }
    // Método para eliminar un producto por su identificador
    @Override
    public void deleteProductById (Long product_id){
        productRepository.deleteById(product_id);
    }
}
