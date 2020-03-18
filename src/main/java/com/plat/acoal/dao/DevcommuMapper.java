package com.plat.acoal.dao;
import com.plat.acoal.entity.Devcommu;
public interface DevcommuMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Devcommu record);
    int insertSelective(Devcommu record);
    Devcommu selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Devcommu record);
    int updateByPrimaryKey(Devcommu record);
}