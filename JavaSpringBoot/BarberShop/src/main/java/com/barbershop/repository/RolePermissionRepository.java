package com.barbershop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barbershop.entity.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

	boolean existsByPermissionId(Long permissionId);
	
	void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
	
	@Query("SELECT u FROM RolePermission u WHERE u.role.id = :roleId")
	List<RolePermission> findPermissionsByRoleId(@Param("roleId") Long roleId);
	
	@Query("SELECT rp FROM RolePermission rp WHERE rp.role.id = :roleId AND rp.permission.id = :permissionId")
	RolePermission findByRoleAndPermissionId(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

}