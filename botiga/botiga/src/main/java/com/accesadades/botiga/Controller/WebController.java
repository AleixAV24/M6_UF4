package com.accesadades.botiga.Controller;

import com.accesadades.botiga.Model.Category;
import com.accesadades.botiga.Model.Subcategory;
import com.accesadades.botiga.Service.CategoryService;
import com.accesadades.botiga.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.accesadades.botiga.Model.Product;
import com.accesadades.botiga.Service.ProductService;
import java.util.Set;

@Controller // Marca esta clase como un controlador de Spring MVC
public class WebController {

    @Autowired // Inyección de dependencia de ProductService
    private ProductService productService;
    @Autowired // Inyección de dependencia de SubCategoryService
    private SubCategoryService subCategoryService;
    // Mapeo para la página principa
    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index"; // Devuelve la vista index.html ubicada en el directorio templates
    }
    // Mapeo para la página de catálogo
    @RequestMapping(value = "/catalog")
    public String catalog(Model model) {
        Set<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "catalog"; // Devuelve la vista catalog.html ubicada en el directorio templates
    }
    // Mapeo para la búsqueda de productos por nombre
    @RequestMapping(value = {"/search", "/prodname"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String searchProductByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            Product product = productService.findProductsByName(name);
            model.addAttribute("product", product);
        }
        return "search"; // Referencia a search.html en el directorio templates
    }
    // Mapeo para mostrar el formulario de creación de productos
    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public String showCreateProducts(Model model){
        Set<Subcategory> subcategories = subCategoryService.findAll();

        model.addAttribute("subcategories", subcategories); // Agrega la lista de subcategorías al modelo
        model.addAttribute("product", new Product()); // Agrega un nuevo objeto Product al modelo
        return "Product"; // Devuelve la vista Product.html ubicada en el directorio templates
    }
    // Mapeo para guardar un nuevo producto
    @RequestMapping (value = "/guardarProducte", method = RequestMethod.POST)
    public String guardarProduct(Model model, @ModelAttribute("product") Product product) {
        if(product == null){
            return "index";
        }
        productService.createProduct(product); // Crea el producto utilizando el servicio ProductService
        return "insert"; // Devuelve la vista insert.html ubicada en el directorio templates
    }
    // Mapeo para eliminar un producto por su identificador
    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam(value = "product_id", required = false) Long product_id, Model model){
        productService.deleteProductById(product_id); // Elimina el producto utilizando el servicio ProductService
    }
}