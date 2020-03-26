package com.plat.acoal.service.impl;

import com.plat.acoal.dao.UserMapper;
import com.plat.acoal.dao.UserRoleMapper;
import com.plat.acoal.entity.UserRole;
import com.plat.acoal.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    public UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> selectAllRoles() {
        return userRoleMapper.selectAllRoles();
    }
}
