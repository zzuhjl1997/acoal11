package com.plat.acoal.controller;
import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Dept;
import com.plat.acoal.service.impl.DeptServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Log4j2
@RequestMapping(value = "/dept",produces = "application/json;charset=UTF-8")
public class DeptController {
    @Autowired
    public DeptServiceImpl dsi;
    @RequestMapping("")
    public String selectAllDept(Dept dept) {
        // User user=new User();
        // Customer customer=new Customer();
        List<Dept> list = dsi.selectAllDepts(dept);
        ResultData resultData=new ResultData();
        resultData.setCode(100);
        if(list.size()>0){
            resultData.setCode(200);
            resultData.setData(list);
        }
        return JSON.toJSONString(resultData);

    }
}
