package com.greencode.GreenMarket.repositorties;

import com.greencode.GreenMarket.entities.Product;
import com.greencode.GreenMarket.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findOneByName(String theRoleName);
}
