package com.greencode.GreenMarket.services;

import com.greencode.GreenMarket.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
}
