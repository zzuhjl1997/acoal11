package com.plat.acoal.service.impl;

import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.dao.ParameterMapper;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.Parameter;
import com.plat.acoal.model.ParameterInfo;
import com.plat.acoal.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterMapper parameterMapper;

    @Autowired
    private DevMapper devMapper;

    /**
     * 添加报警参数
     *
     * @param parameter
     * @return
     */
    public int addParameter(Parameter parameter) {

        return parameterMapper.insertSelective(parameter);
    }

    /**
     * 修改报警参数
     *
     * @param parameter
     * @return
     */
    public void updateParameter(Parameter parameter, Integer ischecked) {
    // 查询当前参数表中是否有该设备,通过以下条件，如果能查到数据，应该只有一条。
        Parameter par = new Parameter();
        par.setIcustomerid(parameter.getIcustomerid());
        par.setDevId(parameter.getDevId());
        par.setCparam(parameter.getCparam());
        par.setGradeid(parameter.getGradeid());
        List<Parameter> parameters = parameterMapper.selectParamByCondition(par);
        // 查找当前客户所有同类型的设备
        List<Dev> devList = devMapper.selectDevByCustomerId(parameter.getIcustomerid(), parameter.getType());
        // 判断当前数据库中是否有该设备；如果有该设备则进行更新，如果没有则进行插入
        if (parameters.size() > 0) {
            parameter.setId(parameter.getId());
            parameter.setUpdatedatetime(new Date());
            parameterMapper.updateByPrimaryKeySelective(parameter);

            if (ischecked!=null&&ischecked == 1) {
                for (int i = 0; i < devList.size(); i++) {
                    Parameter param = new Parameter();
                    param.setIcustomerid(parameter.getIcustomerid());
                    param.setDevId(devList.get(i).getId());
                    System.out.println("参数id："+devList.get(i).getId());
                    param.setCparam(parameter.getCparam());
//                    param.setGradeid(parameter.getGradeid());
                    List<Parameter> paramList = parameterMapper.selectParamByCondition(param);
                    if (paramList.size() > 0) {
                        // 更新一条纪录
                        parameter.setId(paramList.get(0).getId());
                        parameter.setUpdatedatetime(new Date());
                        parameterMapper.updateByPrimaryKeySelective(parameter);
                    } else {
                        // 插入一条新纪录
                        parameter.setId((long) 0);
                        parameter.setDevId(devList.get(i).getId());
                        parameter.setAdddatetime(new Date());
                        parameter.setUpdatedatetime(new Date());
                        parameterMapper.insertSelective(parameter);
                    }
                }
            }
        } else {
            parameter.setAdddatetime(new Date());
            parameter.setUpdatedatetime(new Date());
            parameterMapper.insertSelective(parameter);
            if (ischecked == 1) {
                // 所有同类型设备都设为此值
                for (int i = 0; i < devList.size(); i++) {
                    Parameter param = new Parameter();
                    param.setIcustomerid(parameter.getIcustomerid());
                    param.setDevId(devList.get(i).getId());
                    param.setCparam(parameter.getCparam());
                    param.setGradeid(parameter.getGradeid());
                    List<Parameter> paramList = parameterMapper.selectParamByCondition(param);
                    if (paramList.size() > 0) {
                        // 更新一条纪录
                        parameter.setId(paramList.get(0).getId());
                        parameter.setUpdatedatetime(new Date());
                        parameterMapper.updateByPrimaryKeySelective(parameter);
                    } else {
                        // 插入一条新纪录
                        parameter.setId((long) 0);
                        parameter.setDevId(devList.get(i).getId());
                        parameter.setAdddatetime(new Date());
                        parameter.setUpdatedatetime(new Date());
                        parameterMapper.insertSelective(parameter);
                    }
                }
            }
        }
    }

    /**
     * 查询报警参数
     *
     * @param cparam
     * @param icustomerid
     * @param deviceId
     * @return
     */
    public List<Parameter> selectParamByCondition(String cparam, Integer icustomerid, Integer deviceId) {

        Parameter parameter = new Parameter();
        if (deviceId == 0) {
            parameter.setDevId(deviceId);
            parameter.setCparam(cparam);
            parameter.setIcustomerid(icustomerid);
        } else {
            parameter.setDevId(deviceId);
            parameter.setCparam(cparam);
            parameter.setIcustomerid(icustomerid);
        }
        System.out.println(parameter);
        return parameterMapper.selectParamByCondition(parameter);
    }

/*    public List<Parameter> selectParameterByCus(Parameter parameter) {

        *//*return parameterMapper.selectByCus(parameter);*//*
        return null;
    }*/

    @Override
    public List<ParameterInfo> selectParamInfoByCondition(Map<String, String> condition) {
        return parameterMapper.selectParamInfoByCondition(condition);
    }
}
