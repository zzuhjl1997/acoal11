package com.plat.acoal.service;

import com.plat.acoal.entity.Parameter;
import com.plat.acoal.model.ParameterInfo;

import java.util.List;
import java.util.Map;

public interface ParameterService {
    int addParameter(Parameter parameter);

    void updateParameter(Parameter parameter, Integer ischecked);

    List<Parameter> selectParamByCondition(String cparam, Integer icustomerid, Integer deviceId);
//    List<Parameter> selectParameterByCus(Parameter parameter);

   List<ParameterInfo> selectParamInfoByCondition(Map<String, String> condition);
}
