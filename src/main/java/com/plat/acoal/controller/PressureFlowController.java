package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.*;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.PressureFlowModel;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.HydrantidRelationServiceImpl;
import com.plat.acoal.service.impl.PressureFlowServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/pressureflow", produces = "application/json;charset=UTF-8")
public class PressureFlowController {
    @Autowired
    public PressureFlowServiceImpl pressureFlowServiceImpl;
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    public HydrantidRelationServiceImpl hydrantidRelationServiceImpl;

    /**
     * 查询最新水压
     *
     * @param
     * @return
     */
    @RequestMapping("/newPress")
    public String getNewPress(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
        //获取消防栓Id
        String hid = "102";
        if (request.getParameter("hid") != null && !"".equals(request.getParameter("hid"))) {
            hid = request.getParameter("hid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id

        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(hid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
            System.out.println(pressureFlowModel.getTflow() + "aWRDWSgfaes");
        }
        List<PressureFlowModel> newPress = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
        for (PressureFlowModel d : newPress
        ) {
            Date dt = d.getDcollectdt();
            d.setDcollectdt_re(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newPress);
    }

    /**
     * 查询最新流量
     *
     * @param
     * @return
     */
    @RequestMapping("/newFlow")
    public String getNewFlow(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
//        String devid = "3";
//        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//            devid = request.getParameter("devid");
//        }
        //获取消防栓Id
        String hid = "102";
        if (request.getParameter("hid") != null && !"".equals(request.getParameter("hid"))) {
            hid = request.getParameter("hid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(hid));
        //获取消防栓状态
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getFlowid());
        }
        List<PressureFlowModel> newFlow = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
        System.out.println(newFlow + "adwdqw");
        String[] arrtime = new String[4];
        double[] flow = {0.0, 0.0, 0.0, 0.0};
        int pos = 0;
        for (PressureFlowModel d : newFlow
        ) {
            if (d != null) {
                Date dt = d.getDcollectdt();

                d.setDcollectdt_re(DateUtil.dateToString(dt));
                arrtime[pos] = DateUtil.dateToString(dt);
                flow[pos] = d.getTflow();
                pos++;
            }
        }
        ResultData resultData = new ResultData();
        resultData.setArrsdata1(arrtime);
        resultData.setArrddata3(flow);
        return JSON.toJSONString(resultData);
    }

    /**
     * 消防栓监控列表
     */
    @RequestMapping("/hydrantList")
    private String hydrantList(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer icustomerid = null;
        if (session.getAttribute("icustomerid") != null && !"".equals(session.getAttribute("icustomerid"))) {
            icustomerid = Integer.parseInt(session.getAttribute("icustomerid").toString());
        }
        Integer currentPage = 1;
        Integer pageSize = 1;
        Integer type = null;
        if (condition.containsKey("type")) {
            type = StringUtils.isBlank(condition.get("type")) ? 1 : Integer.valueOf(condition.get("type"));
        }

        if (condition.containsKey("currentPage")) {
//            System.out.println("哈瞌睡的感觉啊上的杰卡斯感到恐惧");
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? 1 : Integer.valueOf(condition.get("pageSize"));
            condition.remove("currentPage");
            condition.remove("pageSize");
        } else {
            currentPage = null;
            pageSize = null;
        }
        Integer pressid = null;
        Integer flowid = null;
        List<PressureFlowModel> listp = new ArrayList<PressureFlowModel>();
        List<PressureFlowModel> listf = new ArrayList<PressureFlowModel>();
        PressureFlowModel pressureFlowModel = new PressureFlowModel();
        //查询消防栓设备列表
        String devname=null;
        if(devname!=null){
            condition.put("name",devname);
        }
        List<DevInfo> listhy = devServiceImpl.selectHydrantList(condition, currentPage, pageSize);
        Integer count = 0;
        for (DevInfo item : listhy
        ) {
            count++;
            item.setCount(count);
            //查询消防栓对应的实时数据
            pressureFlowModel.setPressureid(item.getPressureid());
            if (listp.size() != 0) {

                listp = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);

                System.out.println(listp);

                item.setTpressure(listp.get(0).getTpressure());
            }
            if (listf.size() != 0) {
                pressureFlowModel.setFlowid(item.getFlowid());
                listf = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
                item.setTflow(listf.get(0).getTflow());

            }

        }
        ResultData resultData = new ResultData();
        resultData.setPagecount(count);
        resultData.setData(listhy);
        return JSON.toJSONString(resultData);
    }


    /**
     * 查询一天的水压
     *
     * @param pressureFlowModel
     * @return
     */
    @RequestMapping("/dayPress")
    public String getDayPress(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
//            String devid = "3";
//            if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//                devid = request.getParameter("devid");
//            }
        //获取消防栓Id
        String hid = "1";
        if (request.getParameter("hid") != null && !"".equals(request.getParameter("hid"))) {
            hid = request.getParameter("hid");
        }
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(hid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
        }
        String startdate = "", enddate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfhour = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";
        if (request.getParameter("startdate") != null && !"".equals(request.getParameter("startdate"))) {
            startdate = request.getParameter("startdate");
            startdate += " 00:00:00";
        }
        if (request.getParameter("enddate") != null && !"".equals(request.getParameter("enddate"))) {
            enddate = request.getParameter("enddate");
            enddate += " 23:59:59";
        }
        double[] fPArr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        pressureFlowModel.setDcollectstart(startdate);
        pressureFlowModel.setDcollectend(enddate);
        List<PressureFlowModel> newDust = pressureFlowServiceImpl.selectPByHour(pressureFlowModel);
        ResultData resultData = new ResultData();
        for (PressureFlowModel item : newDust) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = dfhour.format(dt);
            if (item.getTfan() != null && pos < 24) {
                fPArr[pos] = item.getTfan();
            }
            if (item.getTflow() != null && pos < 24) {
                fPArr[pos] = item.getTflow();
            }
            if (item.getTpressure() != null && pos < 24) {
                fPArr[pos] = item.getTpressure();
            }
            pos++;
        }
        resultData.setArrddata1(fPArr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }

    /**
     * 查询一天的水流
     *
     * @param pressureFlowModel
     * @return
     */
    @RequestMapping("/dayFlow")
    public String getDayFlow(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
//        String devid = "3";
//        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//            devid = request.getParameter("devid");
//        }
        //获取消防栓Id
        String hid = "1";
        if (request.getParameter("hid") != null && !"".equals(request.getParameter("hid"))) {
            hid = request.getParameter("hid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(hid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
        }        String startdate = "", enddate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfhour = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";
        if (request.getParameter("startdate") != null && !"".equals(request.getParameter("startdate"))) {
            startdate = request.getParameter("startdate");
            startdate += " 00:00:00";
        }
        if (request.getParameter("enddate") != null && !"".equals(request.getParameter("enddate"))) {
            enddate = request.getParameter("enddate");
            enddate += " 23:59:59";
        }
        double[] fPArr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        pressureFlowModel.setDcollectstart(startdate);
        pressureFlowModel.setDcollectend(enddate);

        List<PressureFlowModel> newDust = pressureFlowServiceImpl.selectFByHour(pressureFlowModel);
        ResultData resultData = new ResultData();
        for (PressureFlowModel item : newDust) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = dfhour.format(dt);
            if (item.getTfan() != null && pos < 24) {
                fPArr[pos] = item.getTfan();
            }
            if (item.getTflow() != null && pos < 24) {
                fPArr[pos] = item.getTflow();
            }
            if (item.getTpressure() != null && pos < 24) {
                fPArr[pos] = item.getTpressure();
            }
            pos++;
        }
        resultData.setArrddata1(fPArr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }
}
