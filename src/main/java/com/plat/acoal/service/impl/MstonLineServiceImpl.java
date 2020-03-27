package com.plat.acoal.service.impl;

import com.plat.acoal.dao.MstonLineMapper;
import com.plat.acoal.entity.MstonLine;
import com.plat.acoal.service.MstonLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MstonLineServiceImpl implements MstonLineService {
    @Autowired
    public MstonLineMapper mstonLineMapper;
    @Override
    public List<MstonLine> selectAllOnline() {
        return mstonLineMapper.selectAllOnline();
    }
}
