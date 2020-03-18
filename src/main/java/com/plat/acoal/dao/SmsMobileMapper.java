package com.plat.acoal.dao;
import com.plat.acoal.entity.SmsMobile;
public interface SmsMobileMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(SmsMobile record);
    int insertSelective(SmsMobile record);
    SmsMobile selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SmsMobile record);
    int updateByPrimaryKey(SmsMobile record);
}