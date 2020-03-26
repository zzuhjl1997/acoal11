package com.plat.acoal.service;

import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.OperationIAO;

import java.util.List;
import java.util.Map;

public interface OperationLogService {
    int addLogs(OperationLog operationLog);
    List<OperationIAO> selectLogs(Map<String,String> condition, Integer currentPage, Integer pageSize);

    Integer selectLogsCount(Map<String, String> condition);
}
