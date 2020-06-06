package com.user.dao;

import com.user.domain.Role;

import java.util.List;

public interface RoleDAO {

    /**
     * 查询所有角色并带用户信息
     * @return
     */
    List<Role> findRoleWithUser();
}
