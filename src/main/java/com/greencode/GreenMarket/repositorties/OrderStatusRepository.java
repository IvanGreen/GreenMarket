package com.greencode.GreenMarket.repositorties;

import com.greencode.GreenMarket.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
}
