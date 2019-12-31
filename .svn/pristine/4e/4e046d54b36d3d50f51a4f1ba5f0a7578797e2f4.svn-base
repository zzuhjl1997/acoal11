package com.plat.acoal.service.impl;

import com.plat.acoal.dao.DeptMapper;
import com.plat.acoal.entity.Dept;
import com.plat.acoal.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    public DeptMapper deptMapper;

    @Override
    public List<Dept> selectAllDepts(Dept dept) {
        return deptMapper.selectAllDepts(dept);
    }
}
