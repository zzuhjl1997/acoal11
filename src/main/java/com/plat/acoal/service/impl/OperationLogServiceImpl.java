package com.plat.acoal.service.impl;

import com.plat.acoal.dao.OperationLogMapper;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.OperationIAO;
import com.plat.acoal.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {
   @Autowired
   private OperationLogMapper operationLogMapper;

    @Override
    public int addLogs(OperationLog operationLog) {
        return  operationLogMapper.insertSelective(operationLog);
    }

    @Override
    public List<OperationIAO> selectLogs(OperationIAO operationIAO,Integer currentPage,Integer pageSize) {
        return operationLogMapper.selectLogs(operationIAO);
    }

}
