package com.plat.acoal.service;

import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.OperationIAO;

import java.util.List;

public interface OperationLogService {
    int addLogs(OperationLog operationLog);
    List<OperationIAO> selectLogs(OperationIAO operationIAO);
}
