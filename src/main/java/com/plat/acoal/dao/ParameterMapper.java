package com.plat.acoal.dao;

import com.plat.acoal.entity.Parameter;

import java.util.List;

public interface ParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);

    List<Parameter> selectByCus(Parameter parameter);

    List<Parameter> selectParamByCondition(Parameter parameter);

}