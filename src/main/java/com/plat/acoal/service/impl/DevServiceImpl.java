package com.plat.acoal.service.impl;
import com.github.pagehelper.PageHelper;
import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.model.*;
import com.plat.acoal.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class DevServiceImpl implements DevService {
    @Autowired
    DevMapper dm;
    @Override
    public List<DevNameModel> selectNameByCondition(Map<String,String> condition) {
        return dm.selectNameByCondition(condition);
    }
    @Override
    public List<DevTypeModel> selectTypeByCondition(Map<String,String> condition) {
        return dm.selectTypeByCondition(condition);
    }
    @Override
    public List<DevInfoModel> selectDevInfoModelByCondtition(Integer currentPage, Map<String, String> condition) {
        if(currentPage != null){
            PageHelper.startPage(currentPage,1);
        }
        return dm.selectDevInfoByCondition(condition);
    }
    @Override
    public List<DevActiveModel> selectDevActiveModelByCondtition(Integer currentPage, Map<String, String> condition) {
        if(currentPage != null){
            PageHelper.startPage(currentPage,1);
        }

        return dm.selectDevActiveModelByCondition(condition);
    }
    @Override
    public Dev selectDevById(Dev dev) {
        return dm.selectByPrimaryKey(dev.getId());
    }
    @Override
    public List<DevInfo> selectFireList(Dev dev) {
        return dm.selectFireByCondition(dev);
    }
    @Override
    public DevInfo selectFireDev(DevInfo devInfo) {
        return dm.selectFireById(devInfo);
    }
    @Override
    public int selectCountByType(Map<String, String> condition) {
        return dm.selectCountByType(condition);
    }
    @Override
    public List<Dev> selectDevByRegion(Integer currentPage, Dev dev) {
        return dm.selectDevInfoByCon(dev);
    }
    @Override
    public List<DevInfo> selectDevInfoByCondition(Map<String, String> condition) {
        return dm.selectDevInfoByCus(condition);
    }
    @Override
    public List<DevInfo> selectCoByCondition(Map<String, String> condition) {
        return dm.selectDevByCondition(condition);
    }
    @Override
    public List<DevInfo> selectDevNowByCondition(Map<String, String> condition) {
        return dm.selectDevNowByCondition(condition);
    }

    @Override
    public List<DevInfo> selectHydrantList(Map<String, String> condition, Integer currentPage, Integer pageSize) {
        if(currentPage!=null){

            PageHelper.startPage(currentPage,pageSize=1);

        }
        return dm.selectHydrantList(condition);
    }

    @Override
    public List<DevInfo> selectCh4ByCondition(Map<String, String> condition) {
        return dm.selectCh4ByCondition(condition);
    }

    @Override
    public List<DevInfo> selectDustByCondition(Map<String, String> condition) {
        return dm.selectDustByCondition(condition);
    }

    @Override
    public List<DevInfo> selectPressNowByCondition(Map<String, String> condition) {
        return dm.selectPressNowByCondition(condition);
    }

    @Override
    public List<DevInfo> selectFlowNowByCondition(Map<String, String> condition) {
        return dm.selectFlowNowByCondition(condition);
    }

    @Override
    public List<DevInfo> selectTemNowByCondition(Map<String, String> condition) {
        return dm.selectTemNowByCondition(condition);
    }

    @Override
    public Integer selectHydrantCount(Map<String, String> condition) {
        return dm.selectHydrantCount(condition);
    }
}
