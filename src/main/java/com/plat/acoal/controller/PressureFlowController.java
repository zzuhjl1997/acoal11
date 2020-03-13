package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.HydrantidRelation;
import com.plat.acoal.entity.Region;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @GetMapping("/newPress")
    public String getNewPress(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
        //获取消防栓Id
        String hid = "1";
        if (request.getParameter("hid") != null && !"".equals(request.getParameter("hid"))) {
            hid = request.getParameter("hid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();


        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(hid));
        pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
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
    @GetMapping("/newFlow")
    public String getNewFlow(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
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
        pressureFlowModel.setDevid(hydrantidRelation_re.getFlowid());
        List<PressureFlowModel> newFlow = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
        String []arrtime=new String[4];
        double []flow={0.0,0.0,0.0,0.0};
        int pos=0;
        for (PressureFlowModel d : newFlow
        ) {
            if (d!=null){
                Date dt = d.getDcollectdt();
                d.setDcollectdt_re(DateUtil.dateToString(dt));
                arrtime[pos]=DateUtil.dateToString(dt);

               flow[pos]=d.getTflow();
               pos++;


            }

        }
        ResultData resultData=new ResultData();
        resultData.setArrsdata1(arrtime);
        resultData.setArrddata3(flow);


        return JSON.toJSONString(resultData);
    }

    /**
     * 水压监控列表
     * @param devInfo
     * @param request
     * @return
     */
/*    @GetMapping("/pList")
    public String getPList(DevInfo devInfo, HttpServletRequest request) {


        List<DevInfo> listinfo = pressureFlowServiceImpl.selectPList(devInfo);
        int count=0;
        for (DevInfo item:listinfo
        ) {
            count ++;
            item.setCount(count);
        }
        return JSON.toJSONString(listinfo);
    }*/

    /**
     * 水流水压监控列表
     * @param devInfo
     * @param request
     * @return
     */
    @GetMapping("/pfList")
    public String getFList(DevInfo devInfo, HttpServletRequest request) {

        List<DevInfo> listinfop = pressureFlowServiceImpl.selectPList(devInfo);
        List<DevInfo> listinfof = pressureFlowServiceImpl.selectFList(devInfo);


        List<DevInfo> listpf=new ArrayList<DevInfo>();
        listpf.addAll(listinfof);
        listpf.addAll(listinfop);

        int count=0;
        for (DevInfo item:listpf
        ) {
            count ++;
            item.setCount(count);
        }
        return JSON.toJSONString(listpf);
    }

    /**
     * 查询一天的水压
     *
     * @param pressureFlowModel
     * @return
     */
    @GetMapping("/dayPress")
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
        pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
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
    @GetMapping("/dayFlow")
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
        pressureFlowModel.setDevid(hydrantidRelation_re.getFlowid());
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










