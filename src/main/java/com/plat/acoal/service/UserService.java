package com.plat.acoal.service;

import com.plat.acoal.entity.User;
import com.plat.acoal.model.UserCustomer;
import com.plat.acoal.utils.JsonResult;

import java.util.List;

public interface UserService {

    User selectUserById(Integer id);

    List<UserCustomer> selectUserByName(String cusername);

    int deleteUserById(Integer id);

    List<UserCustomer> selectAllUserCus(User user);

    int addUser(User user);
    int updateUser(User user);

    JsonResult selectUserByUserName(String username, String password);
}
