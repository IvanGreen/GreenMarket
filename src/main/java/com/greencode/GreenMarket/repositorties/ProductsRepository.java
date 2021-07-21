package com.greencode.GreenMarket.repositorties;

import com.greencode.GreenMarket.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor {
}
