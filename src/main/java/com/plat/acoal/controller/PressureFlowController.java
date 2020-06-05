package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.*;
import com.plat.acoal.model.*;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.HydrantidRelationServiceImpl;
import com.plat.acoal.service.impl.ParameterServiceImpl;
import com.plat.acoal.service.impl.PressureFlowServiceImpl;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.JwtUtils;
import com.plat.acoal.utils.NumUtil;
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
import java.util.concurrent.locks.Condition;

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
    @Autowired
    public ParameterServiceImpl parameterServiceImpl;

    /**
     * 查询最新水压
     *
     * @param
     * @return
     */
    @RequestMapping("/newPress")
    public String getNewPress(PressureFlowModel pressureFlowModel, HttpServletRequest request) {
        //获取消防栓Id
        String devid = "";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id

        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(devid));
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
    public String getNewFlow(PressureFlowModel pressureFlowModel, HttpServletRequest request, @RequestParam Map<String, String> condition) {
//        String devid = "3";
//        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//            devid = request.getParameter("devid");
//        }
        //获取消防栓Id
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防阀门id查询消防阀门开启状态
        int isopen = 0;
        condition.put("devid", devid.toString());
        List<DevInfo> lst = devServiceImpl.selectHStatusByDevid(condition);
        if (lst.size() > 0) {
            for (DevInfo devInfo : lst) {
                if (devInfo != null) {
                    isopen = devInfo.getStatus();
                }
            }
        }
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(devid));
        //查询实时水压
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setPressureid(hydrantidRelation_re.getPressureid());
        }
        String newdate = null;
        List<PressureFlowModel> newPress = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
        for (PressureFlowModel d : newPress
        ) {
            d.setTpressure(NumUtil.dianhoun(d.getTpressure(), 3));
            Date dt = d.getDcollectdt();
            newdate = DateUtil.dateToString(dt);
        }


        //获取消防栓状态
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setFlowid(hydrantidRelation_re.getFlowid());
        }


        //查询实时流量
        List<PressureFlowModel> newFlow = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);


        System.out.println(newFlow + "adwdqw");
        String[] arrtime = new String[24];
        double[] flow = new double[24];

        for (int i = 0; i < 24; i++) {
            arrtime[i] = String.valueOf(i) + ":00";
            flow[i] = 0.0;
        }

        int pos = 0;
        for (PressureFlowModel d : newFlow
        ) {
            if (d != null) {
                Date dt = d.getDcollectdt();
                pos = Integer.parseInt(DateUtil.dateToString(dt).substring(11, 13));
                arrtime[pos] = DateUtil.dateToString(dt, "HH:00");
                flow[pos] = NumUtil.dianhoun(d.getTflow(), 3);
//                pos++;
            }
        }


        ResultData resultData = new ResultData();
        resultData.setDevstatus(isopen);
        resultData.setArrsdata1(arrtime);
        resultData.setDate(newdate);
        resultData.setDatalst3(newPress);
        resultData.setArrddata3(flow);
        resultData.setData(newFlow);

        return JSON.toJSONString(resultData);
    }

    public String selectLastOne(List<PressureFlowModel> list) {
        PressureFlowModel pressureFlowModel = new PressureFlowModel();
        String newdate = null;
        Long dates[] = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            // 把date类型的时间对象转换为long类型，时间越往后，long的值就越大，
            // 所以就依靠这个原理来判断距离现在最近的时间
            dates[i] = list.get(i).getDcollectdt().getTime();
        }
        Long maxIndex = dates[0];// 定义最大值为该数组的第一个数
        for (int j = 0; j < dates.length; j++) {
            if (maxIndex < dates[j]) {
                maxIndex = dates[j];
                // 找到了这个j
                newdate = (DateUtil.dateToString(list.get(j).getDcollectdt()));
            }
        }
        return newdate;
    }


    /**
     * 消防栓监控列表
     */
    @RequestMapping("/hydrantList")
    private String hydrantList(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        condition.put("icustomerid", icustomerid.toString());

        Integer currentPage = 1;
        Integer pageSize = 1;
        Integer type = 10;
        if (condition.containsKey("type")) {
            type = StringUtils.isBlank(condition.get("type")) ? 1 : Integer.valueOf(condition.get("type"));

        }
        if (condition.containsKey("currentPage")) {
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
        String devname = null;
        if (devname != null) {
            condition.put("name", devname);
        }
        if (type != null) {
            condition.put("type", type.toString());
        }
        //查询数量
        int devcount = 0;
        devcount = devServiceImpl.selectCountByType(condition);
        List<DevInfo> listhy = devServiceImpl.selectHydrantList(condition, currentPage, pageSize);
        Integer pagecount = 0;
        Integer count = 0;
        //获取查询的条数
        pagecount = devServiceImpl.selectHydrantCount(condition);
        for (DevInfo item : listhy
        ) {
            count++;
            item.setCount(count);
            if (item != null) {
                //查询消防栓对应的实时数据


                pressureFlowModel.setPressureid(item.getPressureid());
                listp = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);

                System.out.println(listp);
                if (listp.size() != 0) {
                    item.setTpressure(listp.get(0).getTpressure());
                }


                pressureFlowModel.setFlowid(item.getFlowid());
                listf = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
                if (listf.size() != 0) {
                    item.setTflow(listf.get(0).getTflow());
                    item.setLastTime(DateUtil.dateToString(listf.get(0).getDcollectdt(), "yyyy-MM-dd HH:mm:ss"));
                }
            }
        }
        ResultData resultData = new ResultData();
        resultData.setPagecount(pagecount);
        resultData.setData(listhy);
        resultData.setDevcount(devcount);
        return JSON.toJSONString(resultData);
    }


    /**
     * 查询一天的水压
     *
     * @param pressureFlowModel
     * @return
     */
    @RequestMapping("/dayPress")
    public String getDayPress(PressureFlowModel pressureFlowModel, HttpServletRequest request, @RequestParam Map<String, String> condition) {

        //获取消防栓Id
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        condition.remove("devid");
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(devid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getPressureid());
        }
        String startdate = "", enddate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfhour = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";
        if (request.getParameter("date") != null && !"".equals(request.getParameter("date"))) {
            startdate = request.getParameter("date");
            startdate += " 00:00:00";
        }
        if (request.getParameter("date") != null && !"".equals(request.getParameter("date"))) {
            enddate = request.getParameter("date");
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
            if (item.getDcollectdt() != null) {
                Date dt = item.getDcollectdt();
                String da = DateUtil.dateToString(dt);
                pos = Integer.parseInt(DateUtil.dateToString(dt).substring(11, 13));
                arrhours[pos] = dfhour.format(dt);
                if (item.getTpressure() != null && pos < 24) {
                    fPArr[pos] = NumUtil.dianhoun(item.getTpressure(), 3);
                }
            }
//            pos++;
        }

        if (pressureFlowModel.getPressureid() != null) {
            condition.put("devid", pressureFlowModel.getPressureid().toString());
        }
        System.out.println("devid:" + condition.get("devid"));
        condition.put("cparam", "P");
        List<ParameterInfo> listp = new ArrayList<ParameterInfo>();
        List<ParameterInfo> listp_re = new ArrayList<ParameterInfo>();
        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
        if (listp.size() > 0) {
            listp_re = listp;
        } else {
            condition.put("devid", "0");
            System.out.println("devid:" + condition.get("devid"));
            condition.put("cparam", "P");
            listp_re = parameterServiceImpl.selectParamInfoByCondition(condition);
        }
        resultData.setData(listp_re);
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
    public String getDayFlow(PressureFlowModel pressureFlowModel, HttpServletRequest request, Map<String, String> condition) {
        //获取消防栓Id
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }


        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(devid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getFlowid());
        }
        String startdate = "", enddate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfhour = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";
        if (request.getParameter("date") != null && !"".equals(request.getParameter("date"))) {
            startdate = request.getParameter("date");
            startdate += " 00:00:00";
        }
        if (request.getParameter("date") != null && !"".equals(request.getParameter("date"))) {
            enddate = request.getParameter("date");
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
            if (item.getDcollectdt() != null) {
                Date dt = item.getDcollectdt();
                pos = Integer.parseInt(DateUtil.dateToString(dt).substring(11, 13));
                arrhours[pos] = dfhour.format(dt);
                if (item.getTflow() != null && pos < 24) {
                    fPArr[pos] = NumUtil.dianhoun(item.getTflow(), 3);
                }
            }

        }
        if (hydrantidRelation_re != null) {
            condition.put("devid", hydrantidRelation_re.getFlowid().toString());
        } else {
            condition.put("devid", "0");
        }

        condition.put("cparam", "P");
        List<ParameterInfo> listp = new ArrayList<ParameterInfo>();
        List<ParameterInfo> listp_re = new ArrayList<ParameterInfo>();
        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
        if (listp.size() > 0) {
            listp_re = listp;
        } else {
            condition.put("devid", "0");
            System.out.println("devid:" + condition.get("devid"));
            condition.put("cparam", "F");
            listp_re = parameterServiceImpl.selectParamInfoByCondition(condition);
        }
        resultData.setData(listp_re);

        resultData.setArrddata1(fPArr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }

    /**
     * 查询实时水压水流信息
     */
    @RequestMapping("/pfnow")
    private String getpflist(PressureFlowModel pressureFlowModel, HttpServletRequest request, Map<String, String> condition) {
        //先查询一天的水流
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        HydrantidRelation hydrantidRelation = new HydrantidRelation();
        //根据消防栓Id查找设备Id
        HydrantidRelation hydrantidRelation_re = hydrantidRelationServiceImpl.selectHydByHId(Integer.parseInt(devid));
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setDevid(hydrantidRelation_re.getFlowid());
        }
        Date date = new Date();
        String startdate = "", enddate = "";
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";

        double[] fLArr = new double[24];
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

        List<PressureFlowModel> newpf = pressureFlowServiceImpl.selectFByHour(pressureFlowModel);
        ResultData resultData = new ResultData();
        for (PressureFlowModel item : newpf) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = DateUtil.dateToString(dt, "HH:mm");

            if (item.getTflow() != null && pos < 24) {
                fLArr[pos] = item.getTflow();
            }
            pos++;
        }
        if (hydrantidRelation_re != null) {
            pressureFlowModel.setPressureid(hydrantidRelation_re.getPressureid());
            pressureFlowModel.setFlowid(hydrantidRelation_re.getFlowid());
        }

        resultData.setArrddata1(fLArr);
        resultData.setArrsdata1(arrhours);
        List<PressureFlowModel> newPress = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
        List<PressureFlowModel> newflow = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
        String newdate = null;
        if (newPress.size() > 0) {
            newdate = DateUtil.dateToString(newPress.get(0).getDcollectdt(), "yyyy-MM-dd HH:mm:ss");
        }
        resultData.setDate(newdate);
        resultData.setData(newflow);
        resultData.setDatalst3(newPress);
        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value = "/updatehydrant")
    private JsonResult updatehydrant(@RequestParam Map<String, String> condition, HttpSession session, HttpServletRequest request) {
        JsonResult jr = new JsonResult();
        DevActiveModel devActiveModel = new DevActiveModel();
        devActiveModel.setUserId(JwtUtils.getUser(request).getIuserid());
        devActiveModel.setDevId(Integer.parseInt(condition.get("id")));
        if (condition.get("status") != null) {
            jr = devServiceImpl.updatehydrant(condition);

            if (condition.get("status") != null || condition.get("is_auto") != null) {
                jr = devServiceImpl.updatefan(condition);
                if (condition.containsKey("status")) {
                    if (condition.get("status").equals("1")) {
                        devActiveModel.setActflg("1");
                        devActiveModel.setDevActiveOpenTime(new Date());
                    } else if (condition.get("status").equals("0")) {
                        devActiveModel.setActflg("2");
                        devActiveModel.setDevActiveCloseTime(new Date());
                    }
                }
                int i = devServiceImpl.insertActiveInfo(devActiveModel);
            } else if (condition.get("status") == null) {
                jr.setStatus(100);
                jr.setMsg("参数不足");
            }

        }
        return jr;
    }
}


