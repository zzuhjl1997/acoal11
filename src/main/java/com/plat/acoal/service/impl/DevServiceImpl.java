package com.plat.acoal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.DevActive;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.*;
import com.plat.acoal.service.DevService;
import com.plat.acoal.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DevServiceImpl implements DevService {
    @Autowired
    DevMapper dm;


    @Override
    public int insert(Dev dev){
        return dm.insert(dev);
    }

    public int deleteByPrimaryKey(Integer devId){
        return dm.deleteByPrimaryKey(devId);
    }
    @Override
    public List<DevNameModel> selectNameByCondition(Map<String,String> condition) {
        return dm.selectNameByCondition(condition);
    }

    @Override
    public List<DevTypeModel> selectTypeByCondition(Map<String,String> condition) {
        return dm.selectTypeByCondition(condition);
    }

    @Override
    public PageInfo<DevInfoModel> selectDevInfoModelByCondtition(Integer currentPage, Integer pageSize, Map<String, String> condition) {
        if(currentPage != null){
            PageHelper.startPage(currentPage,pageSize);
        }
        List<DevInfoModel> devInfoModelList= dm.selectDevInfoByCondition(condition);
        PageInfo<DevInfoModel> returnDi = new PageInfo<>(devInfoModelList);
        return returnDi;
    }

    @Override
    public PageInfo<DevActiveModel> selectDevActiveModelByCondtition(Integer currentPage,Integer pageSize ,Map<String, String> condition) {
        if(currentPage != null){
            PageHelper.startPage(currentPage,pageSize);
        }
        List<DevActiveModel> devActiveModelList = dm.selectDevActiveModelByCondition(condition);
        PageInfo<DevActiveModel> returnDa = new PageInfo<>(devActiveModelList);
        return returnDa;
    }

    @Override
    public int updateByPrimaryKeySelective(Dev dev){
        return dm.updateByPrimaryKeySelective(dev);
    }

    @Override
    public DevAmountModel selectDevAmountModel(Map<String, String> condition) {
        return dm.selectDevAmountModel(condition);
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
        if (currentPage != null) {

            PageHelper.startPage(currentPage, pageSize);

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

    @Override
    public List<DevInfo> selectDevInfoByDevid(Map<String, String> condition) {
        return dm.selectDevInfoByDevid(condition);
    }

    @Override
    public List<DevInfo> selectFanInfo(Map<String, String> condition) {
        return dm.selectFanInfo(condition);
    }

    @Override
    public List<DevActiveInfo> selectCountOpen(Map<String, String> condition) {
        return dm.selectCountOpen(condition);
    }

    @Override
    public JsonResult updatefan(Map<String, String> condition) {
        JsonResult jr = new JsonResult();

        try {


            int flag = dm.updatefan(condition);
            if (flag > 0) {
                jr.setMsg("修改成功");
                jr.setStatus(200);

            }
        } catch (Exception e) {
            jr.setMsg("修改失败");
            jr.setStatus(300);
        }
        return jr;
    }

    @Override
    public List<DevInfo> selectDevList(Map<String, String> condition) {
        return dm.selectDevList(condition);
    }

    @Override
    public List<Dev> selectDevPByRegion(Integer currentPage, Dev dev) {
        return dm.selectDevPByRegion(dev);
    }

    @Override
    public Integer selectDevCountByType(Map<String, String> condition) {
        return dm.selectDevCountByType(condition);
    }

    @Override
    public JsonResult updatehydrant(Map<String, String> condition) {
        JsonResult jr = new JsonResult();
        try {

            int flag = dm.updatehydrant(condition);
            if (flag > 0) {
                jr.setMsg("修改成功");
                jr.setStatus(200);
            }
        } catch (Exception e) {
            jr.setMsg("修改失败");
            jr.setStatus(300);
        }
        return jr;
    }

    @Override
    public int insertActiveInfo(DevActiveModel devActiveModel) {
        return dm.insertActiveInfo(devActiveModel);
    }
}