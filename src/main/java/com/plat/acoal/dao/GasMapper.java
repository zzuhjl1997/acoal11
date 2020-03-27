package com.plat.acoal.dao;
import com.plat.acoal.entity.Gas;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.GasModel;
import java.util.List;
import java.util.Map;

public interface GasMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Gas record);
    int insertSelective(Gas record);
    Gas selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Gas record);
    int updateByPrimaryKey(Gas record);
    List<Gas> selectGasByRegionId(Integer regionId);
    List<DevInfo> selectCh4List(DevInfo devInfo);
    List<DevInfo> selectCoList(DevInfo devInfo);
    List<GasModel> selectCh4ByHour(GasModel gasModel);
    List<GasModel> selectCoByHour(GasModel gasModel);
    List<GasModel> selectNewCh4ById(GasModel gasModel);
    List<GasModel> selectNewCoById(GasModel gasModel);

    int selectCh4Count(Map<String, String> condition);

    int selectCoCount(Map<String, String> condition);

    List<GasModel> selectGasList(Map<String, String> condition);

    int selectGasCount(Map<String, String> condition);
}