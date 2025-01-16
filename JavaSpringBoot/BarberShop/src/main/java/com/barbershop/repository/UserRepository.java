package com.barbershop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barbershop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findFirstByUserName(String userName);

	@Query("SELECT u FROM User u WHERE u.role.id = :roleId")
	List<User> findUsersByRoleId(@Param("roleId") Long roleId);
}