package com.barbershop.util;

import com.barbershop.entity.User;

public class UserUtil {
    public static boolean isSuperAdmin(User user) {
        return RoleUtil.isSuperAdmin(user.getRole());
    }

    public static boolean isAdmin(User user) {
        return RoleUtil.isAdmin(user.getRole());
    }

    public static boolean isManager(User user) {
        return RoleUtil.isManager(user.getRole());
    }

    public static boolean isClient(User user) {
        return RoleUtil.isClient(user.getRole());
    }
}