package com.greencode.GreenMarket.controllers;

import com.greencode.GreenMarket.entities.Order;
import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.entities.ProductImage;
import com.greencode.GreenMarket.services.ImageSaverService;
import com.greencode.GreenMarket.services.OrderService;
import com.greencode.GreenMarket.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ImageSaverService imageSaverService;

    //http://localhost:8189/app/admin/add_new_product
    @GetMapping("/add_new_product")
    public String getSimpleForm(Model model){
        return "product_form";
    }

    //удаляем продукт
    //http://localhost:8189/app/admin/delete_product?id=
    @GetMapping("/delete_product")
    public String deleteProduct(Model model,
                                @RequestParam("id") Long id,
                                HttpServletRequest httpServletRequest){
        productsService.deleteOne(id);
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }

    //выводим список
    //http://localhost:8189/app/admin/process_form?title=&cost=
    @PostMapping("/process_form")
    public String processForm(@RequestParam("title") String title,
                              @RequestParam("price") Double price,
                              @RequestParam("file") MultipartFile file){
        //если что-то не заполнили, то возвращаем на страницу для заполнения
        if (title == null || price == null || file.isEmpty()){
            return "product_form";
        }
        Product product = new Product(title,price);
        String pathToSavedImage = imageSaverService.saveFile(file);
        ProductImage productImage = new ProductImage();
        productImage.setPath(pathToSavedImage);
        productImage.setProduct(product);
        product.addImage(productImage);
        productsService.saveProduct(product);
        return "product_form_result";
    }

    //выводим форму для обновления
    @GetMapping("/update_form")
    public String updateProduct(Model model,
                                @RequestParam("id") Long id){
        Product product = productsService.getOneById(id);
        model.addAttribute("product",product);
        return "update_product";
    }

    //обновляем один
    //http://localhost:8189/admin/update_one_product?id=&updateTitle=&updatePrice=
    @GetMapping("/update_one_product")
    public String updateOneProduct(Model model,
                                   @RequestParam("id") Long id,
                                   @RequestParam(value = "updateTitle", required = false) String updateTitle,
                                   @RequestParam(value = "updatePrice", required = false) Double updatePrice,
                                   HttpServletRequest httpServletRequest){
        Product updateProduct = new Product(id,updateTitle,updatePrice);

        productsService.updateOne(updateProduct);
        return "update_product_result";
    }

    @GetMapping("/showOrders")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders-page";
    }

    @GetMapping("/showOrder/ready/{id}")
    public void orderReady(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        orderService.changeOrderStatus(order, 2L);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/order/info/{id}")
    public String orderInfo(Model model, @PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);
        model.addAttribute("order",order);
        return "order-info";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable(name = "id") Long id){
        orderService.deleteById(id);
        return "redirect:/admin/showOrders";
    }
}
