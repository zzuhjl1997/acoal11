package com.plat.acoal.service.impl;

import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.dao.DustMapper;
import com.plat.acoal.dao.GasMapper;
import com.plat.acoal.dao.RegionMapper;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.Dust;
import com.plat.acoal.entity.Gas;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.ControlDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControlDeviceServiceImpl implements ControlDeviceService {

    @Autowired
    private RegionMapper regionMapper;

    @Autowired
    private DevMapper devMapper;

    @Autowired
    private GasMapper gasMapper;

    @Autowired
    private DustMapper dustMapper;
    /**
     * 根据客户id查找风机设备
     * @param icustomerid
     * @return
     */
    public List<RegionModel> selectFanDevByCustomerId(Integer icustomerid) {

        List<RegionModel> regionList = regionMapper.selectRegionByCustomerId(icustomerid);
        for (RegionModel region : regionList) {
            // 根据区域id查找区域下所属设备
            List<Dev> devList = devMapper.selectDevByRegionId(region.getId());
            region.setDevs(devList);
            // 根据区域id查找区域下甲烷探测器/一氧化碳的实时浓度
            List<Gas> gasList = gasMapper.selectGasByRegionId(region.getId());
            region.setGases(gasList);
            // 根据区域id查找区域下粉尘的实时浓度
            List<Dust> dustList = dustMapper.selectDustByRegionId(region.getId());
            region.setDusts(dustList);

        }
        return regionList;
    }

    /**
     * 启动/关闭风机设备
     * @param ids
     */
    public void updateFanDevById(String ids,Dev dev) {
        String[] idarr = ids.split(",");

        for (String id : idarr) {
            devMapper.updateByPrimaryKey(dev);
        }
    }
}
