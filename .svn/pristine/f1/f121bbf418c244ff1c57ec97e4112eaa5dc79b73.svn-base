package com.plat.acoal.service.impl;

import com.github.pagehelper.PageHelper;
import com.plat.acoal.dao.OperationLogMapper;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.OperationIAO;
import com.plat.acoal.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OperationLogServiceImpl implements OperationLogService {
   @Autowired
   private OperationLogMapper operationLogMapper;

    @Override
    public int addLogs(OperationLog operationLog) {
        return  operationLogMapper.insertSelective(operationLog);
    }

    @Override
    public List<OperationIAO> selectLogs(Map<String,String> condition, Integer currentPage, Integer pageSize) {

        if(pageSize!=null&&currentPage!=null){
            PageHelper.startPage(currentPage,pageSize);
        }
        return operationLogMapper.selectLogs(condition);
    }

    @Override
    public Integer selectLogsCount(Map<String, String> condition) {
        return operationLogMapper.selectLogsCount(condition);
    }

}
