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

@Controller
public class WebController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SubCategoryService subCategoryService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/catalog")
    public String catalog(Model model) {
        Set<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "catalog";
    }

    @RequestMapping(value = {"/search", "/prodname"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String searchProductByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            Product product = productService.findProductsByName(name);
            model.addAttribute("product", product);
        }
        return "search"; // Referencia a search.html en el directorio templates
    }
    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public String showCreateProducts(Model model){
        Set<Subcategory> subcategories = subCategoryService.findAll();

        model.addAttribute("subcategories", subcategories);
        model.addAttribute("product", new Product());
        return "Product";
    }
    @RequestMapping (value = "/guardarProducte", method = RequestMethod.POST)
    public String guardarProduct(Model model, @ModelAttribute("product") Product product) {
        if(product == null){
            return "index";
        }
        productService.createProduct(product);
        return "insert";
    }
    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam(value = "product_id", required = false) Long product_id, Model model){
        productService.deleteProductById(product_id);
    }
}