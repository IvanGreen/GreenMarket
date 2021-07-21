package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.repositorties.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable){
        return productsRepository.findAll(spec,pageable);
    }

    public Product getOneById(Long id) { return productsRepository.getById(id); }

    public List<Product> getAllProducts() { return productsRepository.findAll(); }

    public Product saveProduct(Product product) { return productsRepository.save(product); }

    public void deleteAll() { productsRepository.deleteAll(); }

    public void deleteOne(Long id) { productsRepository.deleteById(id); }

    public void updateOne(Product product){
        Product oldProduct = productsRepository.getById(product.getId());

        if (product.getTitle() != null) oldProduct.setTitle(product.getTitle());
        if (product.getPrice() != 0) oldProduct.setPrice(product.getPrice());

        saveProduct(oldProduct);
    }
}
