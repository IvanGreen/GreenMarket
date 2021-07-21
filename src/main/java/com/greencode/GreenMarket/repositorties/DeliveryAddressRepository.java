package com.greencode.GreenMarket.repositorties;

import com.greencode.GreenMarket.entities.DeliveryAddress;
import com.greencode.GreenMarket.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress,Long> {
    List<DeliveryAddress> findAllByUserId(Long id);
}
