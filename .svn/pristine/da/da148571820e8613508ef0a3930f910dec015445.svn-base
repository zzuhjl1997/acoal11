package com.plat.acoal.service;

import com.plat.acoal.entity.User;
import com.plat.acoal.model.UserCustomer;
import com.plat.acoal.utils.JsonResult;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectUserById(Integer id);

    List<UserCustomer> selectUserByName(String cusername);

    int deleteUserById(Integer id);

    List<UserCustomer> selectAllUserCus(Map<String,String> condition,Integer currentPage,Integer pageSize);

    int addUser(User user);
    int updateUser(User user);

    int selectAllUserCount(Map<String, String> condition);

    User selectUserByUsername(String username);
}
