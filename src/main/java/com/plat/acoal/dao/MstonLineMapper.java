package com.plat.acoal.dao;

import com.plat.acoal.entity.MstonLine;

import java.util.List;

public interface MstonLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MstonLine record);

    int insertSelective(MstonLine record);

    MstonLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MstonLine record);

    int updateByPrimaryKey(MstonLine record);

    List<MstonLine> selectAllOnline();
}