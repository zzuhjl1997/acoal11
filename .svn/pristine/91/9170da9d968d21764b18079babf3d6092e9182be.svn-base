package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Dept;
import com.plat.acoal.entity.UserRole;
import com.plat.acoal.service.impl.DeptServiceImpl;
import com.plat.acoal.service.impl.UserRoleServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/userrole",produces = "application/json;charset=UTF-8")
public class UserRoleController {
    @Autowired
    public UserRoleServiceImpl ursi;
    @RequestMapping("")
    public String selectAllDept(Dept dept) {

        List<UserRole> list = ursi.selectAllRoles();
        ResultData resultData=new ResultData();
        resultData.setCode(100);
        if(list.size()>0){
            resultData.setCode(200);
            resultData.setData(list);
        }
        return JSON.toJSONString(resultData);

    }
}

