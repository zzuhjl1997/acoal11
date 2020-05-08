package com.plat.acoal.dao;

import com.plat.acoal.entity.Parameter;
import com.plat.acoal.model.ParameterInfo;

import java.util.List;
import java.util.Map;

public interface ParameterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);

    List<Parameter> selectParamByCondition(Parameter parameter);

    List<ParameterInfo> selectParamInfoByCondition(Map<String, String> condition);
}