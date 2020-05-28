package com.plat.acoal.service.impl;

import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.dao.ParameterMapper;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.Parameter;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.ParameterInfo;
import com.plat.acoal.service.ParameterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Override
    public int addParameter(Parameter parameter) {

        return parameterMapper.insertSelective(parameter);
    }

    /**
     * 修改报警参数
     *
     * @param parameter
     * @return
     */
    @Override
    public void updateParameter(Parameter parameter, Integer ischecked) {
        parameter.setAdddatetime(new Date());
        parameter.setUpdatedatetime(new Date());
        //看看devid=0的对应参数值是否存在
        ParameterInfo parameterInfo = new ParameterInfo();
        parameterInfo.setDevId(0);
        parameterInfo.setCparam(parameter.getCparam());
        parameterInfo.setIcustomerid(parameter.getIcustomerid());
        List<ParameterInfo> parameters0 = parameterMapper.selectPaByCondition(parameterInfo);

        //若是devid=0的值不存在,insert devid=0

        Parameter parameter1 = new Parameter();
        //将一个对象的属性值赋值给另一个对象, 属性名需要相同
        BeanUtils.copyProperties(parameter, parameter1);
        parameter1.setDevId(0);
        if (parameters0.size() < 1) {
            parameterMapper.insertSelective(parameter1);
        }

        //判断该参数是否已经存在
        parameterInfo.setDevId(parameter.getDevId());
        List<ParameterInfo> parameters = parameterMapper.selectPaByCondition(parameterInfo);
        //若存在，update
        if (parameters.size() > 0) {
            parameterMapper.updateByPrimaryKeySelective(parameter);
        }
        //若不存在,insert
        else {
            parameterMapper.insertSelective(parameter);
        }
        //看看需不需要所有设备与此同值
        if (ischecked != null && ischecked == 1) {
            List<Dev> devList = devMapper.selectDevByCustomerId(parameter.getIcustomerid(), parameter.getType());
            for (Dev dev : devList) {
                //获取设备列表 对每一个设备id的参数进行判断  看看对应设备id是否存在
                parameterInfo.setDevId(dev.getId());
                List<ParameterInfo> pars = parameterMapper.selectPaByCondition(parameterInfo);
                //若存在update   若不存在 不管了
                if (pars.size() > 0) {
                    parameter.setDevId(0);
                    parameterMapper.updateByPrimaryKeySelective(parameter);
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
    @Override
    public List<ParameterInfo> selectParamByCondition(String cparam, Integer icustomerid, Integer deviceId) {

        Parameter parameter = new Parameter();
        //查询该devid对应的type


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


    @Override
    public List<ParameterInfo> selectParamInfoByCondition(Map<String, String> condition) {
        return parameterMapper.selectParamInfoByCondition(condition);
    }
}
