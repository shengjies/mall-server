package com.code.mallservice.mall.utils;

import com.code.mallservice.mall.entity.UserEntity;

public class AuthorUtils {
    public static int getUserRole(UserEntity entity) {
        if (entity == null) {
            return -1;
        }
        return entity.getRole_code().equals("role_user") || entity.getRole_code().equals("role_group") ? entity.getId() : -1;
    }

    public static int userRole(UserEntity entity) {
        int code = -1;
        switch (entity.getRole_code()) {
            case "role_group":
                code = 1;
                break;
            case "role_user":
                code = 2;
                break;
            default:
                code = -1;
                break;
        }
        return code;
    }
}
