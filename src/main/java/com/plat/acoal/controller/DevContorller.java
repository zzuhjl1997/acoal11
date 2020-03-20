package com.plat.acoal.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.*;
import com.plat.acoal.model.AlarmInfo;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.DevInfoModel;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.RegionService;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.RegionServiceImpl;
import com.plat.acoal.utils.NumberUtil;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@Log4j2
@RequestMapping(value = "/dev", produces = "application/json;charset=UTF-8")
public class DevContorller {
    @Autowired
    private DevServiceImpl dsi;
    @Autowired
    private AlarmServiceImpl alarmServiceImpl;
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    private RegionServiceImpl regionServiceImpl;
     @RequestMapping("/dim")
    public String selectDevInfoModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        return JSON.toJSONString(dsi.selectDevInfoModelByCondtition(currentPage, condition));
    }
     @RequestMapping("/name")
    public String selectNameByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        return JSON.toJSONString(dsi.selectNameByCondition(condition));
    }
     @RequestMapping("/type")
    public String selectTypeByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerid = user.getIcustomerid();
        condition.put("customer",customer);
        */
        return JSON.toJSONString(dsi.selectTypeByCondition(condition));
    }
     @RequestMapping("/dam")
   
    public String selectDevActiveModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        return JSON.toJSONString(dsi.selectDevActiveModelByCondtition(currentPage, condition));
    }
    ;
    /**
     * 查询消防炮状态列表
     */
     @RequestMapping("/fire")
    public String getFireList(Dev dev, HttpServletRequest request) {
//
//        String devid = "3";
//        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//            devid = request.getParameter("devid");
//        }
        //查询消防炮列表监测数据
        List<DevInfo> lst = dsi.selectFireList(dev);
        //查询对应的消防炮的报警状态
        AlarmInfo alarmInfo = new AlarmInfo();
        for (DevInfo item : lst
        ) {
            alarmInfo.setDevid(item.getId());
            List<AlarmInfo> lsta = alarmServiceImpl.selectAlarmInfoByCondition(alarmInfo);
            if (lsta.size() > 0) {
                item.setAlarm(1);
                item.setUpdateTime(lsta.get(0).getAlarmTime());
            }
        }
        return JSON.toJSONString(lst);
    }
    /**
     * 查询消防炮状态
     */
     @RequestMapping("/nowFire")
    public String getFireStatus(DevInfo devInfo, HttpServletRequest request) {
        String devid = "8";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        devInfo.setId(Integer.parseInt(devid));
        //查询消防炮监测数据
        DevInfo devInfo1 = new DevInfo();
        devInfo1 = dsi.selectFireDev(devInfo);
        if (devInfo != null) {
            //查询对应的消防炮的报警状态
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setDevid(devInfo.getId());
            List<AlarmInfo> lsta = alarmServiceImpl.selectAlarmInfoByCondition(alarmInfo);
            if (lsta.size() > 0) {
                devInfo1.setAlarm(1);
                devInfo1.setUpdateTime(lsta.get(0).getAlarmTime());
            }
        }
        return JSON.toJSONString(devInfo1);
    }
    /**
     * 获取设备基础信息
     *
     * @param dev
     * @param request
     * @return
     */
     @RequestMapping("/newstatus")
    public String getStatus(@RequestParam Map<String, String> condition, Dev dev, HttpServletRequest request) {
        String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        dev.setId(Integer.parseInt(devid));
        Dev newDev = devServiceImpl.selectDevById(dev);
        //获取该类设备总数
        int sum = 0;
        if (newDev != null) {
            int type = newDev.getType();
            sum = devServiceImpl.selectCountByType(condition);
        }
        ResultData resultData = new ResultData();
        resultData.setDev(dev);
        resultData.setCount(sum);
        return JSON.toJSONString(resultData);
    }
    /**
     * 获取监测点列表  树状图
     */
     @RequestMapping("/devlist")
    private String getdevlist(@RequestParam Map<String, String> condition, HttpSession session) {
       /* User user = (User)session.getAttribute("user");
        Integer icustomerid = user.getIcustomerid();*/
        Integer customerId = 2;
        Region region = new Region();
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        Integer regionId = 1;
        List<RegionModel> listrg = regionServiceImpl.selectRegionByCus(customerId);
        List<RegionModel> listrg_re = new ArrayList<RegionModel>();
//        List<Dev> listdev=new ArrayList<Dev>();
        for (RegionModel item : listrg
        ) {
            Dev dev = new Dev();
            if (item != null) {
                dev.setIcustomerid(customerId);
                dev.setRegion(item.getId());
            }
            List<Dev> listdev = devServiceImpl.selectDevByRegion(currentPage, dev);
            item.setDevs(listdev);
            listrg_re.add(item);
        }
        return JSON.toJSONString(listrg_re, SerializerFeature.DisableCircularReferenceDetect);
    }
    /**
     * 获取风机的列表 或者其他设备的列表
     *
     * @param
     */
     @RequestMapping("/fanlist")
    private String getfanlist(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer customerId = 2;
        Integer count = null;
        List<DevInfo> list = devServiceImpl.selectDevInfoByCondition(condition);
        count = devServiceImpl.selectCountByType(condition);
        ResultData resultData = new ResultData();
        resultData.setData(list);
        resultData.setCount(count);
        return JSON.toJSONString(resultData, SerializerFeature.DisableCircularReferenceDetect);
    }
    /**
     * 获取设备在线率  首页》》设备在线率
     * 思路：查询每个种类设备的总数
     * 设置status的值为1时的在线设备总数
     * 在线/总设备=设备在线率
     * List(Map<String,Integer>)
     */
     @RequestMapping("/onlinerate")
    private String onlinerate(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer customerId = 2;
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> param_tem = new HashMap<String, String>();
        Map<String, String> param_co = new HashMap<String, String>();
        Map<String, String> param_ch4 = new HashMap<String, String>();
        Map<String, String> param_dust = new HashMap<String, String>();
        Map<String, String> param_press = new HashMap<String, String>();
        Map<String, String> param_flow = new HashMap<String, String>();
        //红外线温度
        Integer count_tem = null;
        Integer count_tem_online = null;
        double tem_online = 0.0;
        condition.put("type", "2");
        count_tem = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_tem_online = devServiceImpl.selectCountByType(condition);
        tem_online = count_tem_online / count_tem;
        param_tem.put("tem_online", String.valueOf(NumberUtil.DecimalToPercent(tem_online)));
        list.add(param_tem);
        condition.put("status", "null");
        //co
        Integer count_co = null;
        Integer count_co_online = null;
        double co_online = 0.0;
        condition.put("type", "5");
        count_co = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_co_online = devServiceImpl.selectCountByType(condition);
        co_online = count_co_online / count_co;
        param_co.put("co_online", String.valueOf(NumberUtil.DecimalToPercent(co_online)));
        list.add(param_co);
        condition.put("status", "null");
        //ch4
        Integer count_ch4 = null;
        Integer count_ch4_online = null;
        double ch4_online = 0.0;
        condition.put("status", "null");
        condition.put("type", "6");
        count_ch4 = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_ch4_online = devServiceImpl.selectCountByType(condition);
        ch4_online = count_ch4_online / count_ch4;
        param_ch4.put("ch4_online", String.valueOf(NumberUtil.DecimalToPercent(ch4_online)));
        list.add(param_ch4);
        condition.put("status", "null");
        //粉尘
        Integer count_dust = null;
        Integer count_dust_online = null;
        double dust_online = 0.0;
        condition.put("status", "null");
        condition.put("type", "4");
        count_dust = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_dust_online = devServiceImpl.selectCountByType(condition);
        dust_online = count_dust_online / count_dust;
        param_dust.put("dust_online", String.valueOf(NumberUtil.DecimalToPercent(dust_online)));
        list.add(param_dust);
        condition.put("status", "null");
        //水压
        Integer count_press = null;
        Integer count_press_online = null;
        double press_online = 0.0;
        condition.put("type", "7");
        count_press = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_press_online = devServiceImpl.selectCountByType(condition);
        dust_online = count_press_online / count_press;
        param_press.put("press_online", String.valueOf(NumberUtil.DecimalToPercent(press_online)));
        list.add(param_press);
        condition.put("status", "null");
        //流量
        Integer count_flow = null;
        Integer count_flow_online = null;
        double flow_online = 0.0;
        condition.put("type", "8");
        count_flow = devServiceImpl.selectCountByType(condition);
        condition.put("status", "1");
        count_flow_online = devServiceImpl.selectCountByType(condition);
        flow_online = count_flow_online / count_flow;
        param_flow.put("flow_online", String.valueOf(NumberUtil.DecimalToPercent(flow_online)));
        list.add(param_flow);
//        resultData.setCount(count);
        return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
    }
    /**
     * 获取大屏实时数据 三个表格 三个坐标轴
     */
    @RequestMapping("/realtimedata")
    private String realtimedata(@RequestParam Map<String, String> condition, HttpSession session){
        Integer customerId = 2;
        if(condition.containsKey("customerId")){
            customerId=StringUtils.isBlank(condition.get("customerId"))? 1:Integer.valueOf(condition.get("customerId"));
        }
        //获取CO,CH4,粉尘实时数据
        int pos=0;
        String[] press_region=new String[4];
        double[] press_value=new double[4];
        String[] tem_region=new String[4];
        double[] tem_value=new double[4];
        String[] flow_region=new String[4];
        double[] flow_value=new double[4];
        condition.put("type","5");
        List<DevInfo> list_co=devServiceImpl.selectCoByCondition(condition);
        condition.put("type","6");
        List<DevInfo> list_ch4=devServiceImpl.selectCh4ByCondition(condition);
        condition.put("type","4");
        List<DevInfo> list_dust=devServiceImpl.selectDustByCondition(condition);
        //获取区域水压温度流量
        //获取水压
        condition.put("type","7");
        List<DevInfo> list_press=devServiceImpl.selectPressNowByCondition(condition);
//        System.out.println("dstsuigfise"+list_press);
        pos=0;
        for (DevInfo item:list_press
             ) {
            press_region[pos]=item.getRegionname();
            press_value[pos]=item.getTpressure();
            pos ++;
        }
        //获取区域水流数据
        condition.put("type","8");
        List<DevInfo> list_flow=devServiceImpl.selectFlowNowByCondition(condition);
        System.out.println("dstsuigfise"+list_flow);
        pos=0;
        for (DevInfo item:list_flow
        ) {
            flow_region[pos]=item.getRegionname();
            flow_value[pos]=item.getTpressure();
            pos ++;
        }
        //获取区域温度
        condition.put("type","2");
        List<DevInfo> list_tem=devServiceImpl.selectTemNowByCondition(condition);
        for (DevInfo item:list_flow
        ) {
            tem_region[pos]=item.getRegionname();
            tem_value[pos]=item.getTpressure();
            pos ++;
        }



        ResultData resultData=new ResultData();
        resultData.setData(list_ch4);
        resultData.setDatalst2(list_co);
        resultData.setDatalst3(list_dust);
        resultData.setArrsdata1(press_region);
        resultData.setArrddata1(press_value);
        resultData.setArrsdatax1(flow_region);
        resultData.setArrddata2(flow_value);
        return JSON.toJSONString(resultData, SerializerFeature.DisableCircularReferenceDetect);
    }
}
