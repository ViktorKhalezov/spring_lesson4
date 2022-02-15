package ru.geekbrains.spring_lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;


    @RequestMapping(path = "/form", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-form";
    }

    @RequestMapping(path = "/form", method = RequestMethod.POST)
    public String fillForm(Product product) {
        appService.saveProduct(product);
        return "redirect:/products";
    }


    @RequestMapping
    public String getAllProducts(Model model, @RequestParam(required = false) String sort) {
        try {
            if (sort.equals("asc")) {
                model.addAttribute("products", appService.getProductsSortedAsc());
            }
            if (sort.equals("desc")) {
                model.addAttribute("products", appService.getProductsSortedDesc());
            }
        } catch(NullPointerException e) {
            model.addAttribute("products", appService.getAllProducts());
        }
        return "product-list";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String getProductById(Model model, @PathVariable Long id) {
        Product product = appService.getProductById(id);
        model.addAttribute("product", product);
        return "product-view";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteById(@RequestParam Long id) {
        appService.deleteById(id);
        return "redirect:/products";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.GET)
    public String editById(Model model, @RequestParam Long id) {
           Product product = appService.getProductById(id);
           model.addAttribute("product", product);
           return "create-form";
    }


}
