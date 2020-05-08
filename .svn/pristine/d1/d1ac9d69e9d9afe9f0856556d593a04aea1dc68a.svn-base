package com.plat.acoal.dao;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.OperationIAO;
import java.util.List;
import java.util.Map;

public interface OperationLogMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(OperationLog record);
    int insertSelective(OperationLog record);
    OperationLog selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(OperationLog record);
    int updateByPrimaryKey(OperationLog record);
    List<OperationIAO> selectLogs(Map<String,String> condition);

    Integer selectLogsCount(Map<String, String> condition);
}