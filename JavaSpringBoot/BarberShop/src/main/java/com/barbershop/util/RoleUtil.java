package com.barbershop.util;

import com.barbershop.entity.Role;

public class RoleUtil {
    public static boolean isSuperAdmin(Role role) {
        return "SuperAdmin".equals(role.getName());
    }

    public static boolean isAdmin(Role role) {
        return "Admin".equals(role.getName());
    }

    public static boolean isManager(Role role) {
        return "Manager".equals(role.getName());
    }

    public static boolean isClient(Role role) {
        return "Client".equals(role.getName());
    }
}