package com.greencode.GreenMarket.utils;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Getter
public class ProductSort {
    private StringBuilder filterDefinition;

    public Sort setSort(Map<String,String> params){
        this.filterDefinition = new StringBuilder();

        if (params.containsKey("sort_price")){
            if (params.get("sort_price").equals("min")) {
                filterDefinition.append("$sort_price").append("min");
                return Sort.by(Sort.Direction.ASC,  "price");
            }
            if (params.get("sort_price").equals("max")) {
                filterDefinition.append("$sort_price").append("max");
                return Sort.by(Sort.Direction.DESC, "price");
            }
        }
        return Sort.by(Sort.Direction.ASC,"id");
    }
}
