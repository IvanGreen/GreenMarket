package com.greencode.GreenMarket.repositorties;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUserName(String userName);
}
