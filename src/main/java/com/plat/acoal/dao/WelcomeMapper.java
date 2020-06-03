package com.plat.acoal.dao;

import com.plat.acoal.entity.Welcome;
import com.plat.acoal.model.WelcomeInfo;

import java.util.List;
import java.util.Map;

public interface WelcomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Welcome record);

    int insertSelective(Welcome record);

    Welcome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Welcome record);

    int updateByPrimaryKey(Welcome record);

    List<WelcomeInfo> selectwelcome(Map<String, String> condition);

    int addWord(Map<String, String> condition);

    int updateword(Map<String, String> condition);

    int deleteWord(Map<String, String> condition);
}