package com.plat.acoal.service.impl;

import com.github.pagehelper.PageHelper;
import com.plat.acoal.dao.UserMapper;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.UserCustomer;
import com.plat.acoal.service.UserService;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.MD5Utils;
import com.plat.acoal.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    public User selectUserById(Integer id) {

        return  userMapper.selectByPrimaryKey(id);
    }



    @Override
    public List<UserCustomer> selectUserByName(String cusername) {

        return userMapper.selectUserByName(cusername);
    }

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    public int deleteUserById(Integer id) {

        return  userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserCustomer> selectAllUserCus(Map<String,String> condition,Integer currentPage,Integer pageSize) {
        System.out.println(currentPage+"当前页");
        if(currentPage != null&&pageSize!=null){
            PageHelper.startPage(currentPage,pageSize);
        }

        return userMapper.selectAllUserCus(condition);
    }

    @Override
    public int addUser(User user) {
        user.setIuserid(UUIDUtil.getUUIDInOrderId());
        user.setCpassword(MD5Utils.md5(user.getCpassword()));
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int selectAllUserCount(Map<String, String> condition) {

        return userMapper.selectAllUserCount(condition);
    }

    /**
     * 根据用户名查询用户实体，登录
     * @param username
     * @return
     */
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

}
