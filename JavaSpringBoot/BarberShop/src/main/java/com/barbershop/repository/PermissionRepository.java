package com.barbershop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barbershop.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	@Query("SELECT p FROM Permission p WHERE p.name = :name")
	List<Permission> findByName(@Param("name") String name);
}