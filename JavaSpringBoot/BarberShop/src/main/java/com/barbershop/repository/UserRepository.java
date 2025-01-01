package com.barbershop.repository;

import com.barbershop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameAndPassword(String userName, String password);
}
