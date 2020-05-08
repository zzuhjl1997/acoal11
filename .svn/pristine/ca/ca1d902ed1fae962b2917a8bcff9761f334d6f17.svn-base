package com.plat.acoal.service.impl;
import com.github.pagehelper.PageHelper;
import com.plat.acoal.dao.DeptMapper;
import com.plat.acoal.dao.DustMapper;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.DustModel;
import com.plat.acoal.service.DustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class DustServiceImpl implements DustService {
    @Autowired
    public DustMapper dustMapper;
    @Override
    public List<DustModel> selectNewInfoById(DustModel dustModel) {
        return dustMapper.selectNewInfoById(dustModel);
    }
    @Override
    public List<DustModel> selectInfoByHour(DustModel dustModel) {
        return dustMapper.selectInfoByHour(dustModel);
    }
    @Override
    public List<DevInfo> selectDustList(DevInfo devInfo,Integer currentPage,Integer pageSize) {
        if(currentPage != null&&pageSize!=null){
            PageHelper.startPage(currentPage,pageSize);
        }
        return dustMapper.selectDustList(devInfo);
    }

    @Override
    public int selectDustCount(Map<String, String> condition) {
        return dustMapper.selectDustCount(condition);
    }
}
