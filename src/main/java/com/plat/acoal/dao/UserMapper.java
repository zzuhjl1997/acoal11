package com.plat.acoal.dao;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.UserCustomer;
import java.util.List;
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(Integer id);
    List<UserCustomer> selectUserByName(String cusername);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
    List<User> selectAllUser();
    List<UserCustomer> selectAllUserCus(User user);
    User selectUserByUserName(String username);
}