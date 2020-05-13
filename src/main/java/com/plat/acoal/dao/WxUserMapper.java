package com.plat.acoal.dao;
import com.plat.acoal.entity.WxUser;

import java.util.List;

public interface WxUserMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByOpenId(String openid);

    int insert(WxUser record);

    int insertSelective(WxUser record);

    WxUser selectByPrimaryKey(Integer id);

    WxUser selectByUserID(Integer id);

    List<WxUser> selectOpenidList(Integer id);

    List<WxUser> selectList();

    int updateByPrimaryKeySelective(WxUser record);

    int updateByPrimaryKey(WxUser record);

    int selectByOpenId(String openid);

    int updateByopenId(WxUser wu);


}