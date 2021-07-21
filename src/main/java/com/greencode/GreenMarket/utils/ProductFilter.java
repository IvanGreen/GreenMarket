package com.greencode.GreenMarket.utils;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.services.specifications.ProductSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String,String> map){
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();

        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGEThan(minPrice));
            filterDefinition.append("&min_price").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLEThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
    }
}
