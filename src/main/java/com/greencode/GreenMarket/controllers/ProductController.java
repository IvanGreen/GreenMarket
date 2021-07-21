package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.services.ProductsService;
import com.greencode.GreenMarket.utils.ProductFilter;
import com.greencode.GreenMarket.utils.ProductSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
//    @Autowired
//    private ProductService productsService;

    @Autowired
    private ProductsService productsService;

    //показываем все продукты
    //http://localhost:8189/app/product/showAllProducts
    @GetMapping("/showAllProducts")
    public String showAllProducts(Model model, @RequestParam Map<String,String> params){

        ProductSort ps = new ProductSort();

        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }

        Pageable pageRequest = PageRequest.of(pageIndex, 5, ps.setSort(params));
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> page = productsService.findAll(productFilter.getSpec(), pageRequest);

        model.addAttribute("filtersDef", productFilter.getFilterDefinition().append(ps.getFilterDefinition()));
        model.addAttribute("page",page);
        return "all_products";
    }

}
