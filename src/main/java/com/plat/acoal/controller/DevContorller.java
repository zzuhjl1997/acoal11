package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.*;
import com.plat.acoal.model.*;
import com.plat.acoal.service.RegionService;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.RegionServiceImpl;
import com.plat.acoal.utils.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.*;

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

    @GetMapping("/dim")
    public String selectDevInfoModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        Integer pageSize = Integer.MAX_VALUE;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.parseInt(condition.get("currentPage"));
            condition.remove("currentPage");
        }
        if (condition.containsKey("pageSize")) {
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? Integer.MAX_VALUE : Integer.parseInt(condition.get("pageSize"));
            condition.remove("pageSize");
        } else {
            pageSize = Integer.MAX_VALUE;
        }
        return JSON.toJSONString(dsi.selectDevInfoModelByCondtition(currentPage, pageSize, condition));
    }

    @GetMapping("/name")
    public String selectNameByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        return JSON.toJSONString(dsi.selectNameByCondition(condition));
    }

    @GetMapping("/type")
    public String selectTypeByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerid = user.getIcustomerid();
        condition.put("customer",customer);
        */
        return JSON.toJSONString(dsi.selectTypeByCondition(condition));
    }

    @GetMapping("/dam")
    public String selectDevActiveModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        Integer pageSize = Integer.MAX_VALUE;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        }
        if (condition.containsKey("pageSize")) {
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? Integer.MAX_VALUE : Integer.valueOf(condition.get("pageSize"));
            condition.remove("pageSize");
        } else {
            pageSize = Integer.MAX_VALUE;
        }
        return JSON.toJSONString(dsi.selectDevActiveModelByCondtition(currentPage, pageSize, condition));
    }

    @GetMapping("/damb")
    public String selectDevAmountModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(dsi.selectDevAmountModel(condition));
    }

    @PostMapping("")
    public String insert(Dev dev, HttpSession session) {
        JsonResult jr = new JsonResult();
        int num = dsi.insert(dev);
        if (num == 1) {
            jr.setMsg("添加成功");
            jr.setStatus(200);
        } else if (num < 1) {
            jr.setMsg("添加失败");
            jr.setStatus(500);
        } else {
            jr.setMsg("异常！可能出现增加多次记录！");
            jr.setStatus(555);
        }
        return JSON.toJSONString(jr);
    }

    @DeleteMapping("")
    public String deleteByPrimaryKey(@RequestParam(value = "devId") Integer devId) {
        JsonResult jr = new JsonResult();
        System.out.println("devId::::::::::::" + devId);
        int num = dsi.deleteByPrimaryKey(devId);
        if (num == 1) {
            jr.setMsg("删除成功");
            jr.setStatus(200);
        } else if (num < 1) {
            jr.setMsg("删除失败");
            jr.setStatus(500);
        } else {
            jr.setMsg("异常！可能出现删除多次记录！");
            jr.setStatus(555);
        }
        return JSON.toJSONString(jr);
    }

    @PutMapping("")
    public String update(Dev dev) {
        JsonResult jr = new JsonResult();
        int a = dsi.updateByPrimaryKeySelective(dev);
        if (a == 1) {
            jr.setStatus(200);
            jr.setMsg("修改成功");
        } else if (a < 1) {
            jr.setStatus(400);
            jr.setMsg("修改失败");
        } else if (a > 1) {
            jr.setStatus(500);
            jr.setMsg("修改异常");
        }
        return JSON.toJSONString(jr);
    }

    /**
     * 查询消防炮状态列表
     */
    @RequestMapping("/fire")
    public String getFireList(Dev dev, HttpServletRequest request) {

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

        //查询基础信息
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -30);
        String c = cal.getTime().toString();
        System.out.println(cal.getTime());
        String starttime = DateUtil.dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
        String endtime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        int alacount = 0;
        condition.put("starttime", starttime);
        condition.put("endtime", endtime);
        int sum = 0;
        List<DevInfo> listd = new ArrayList<DevInfo>();

        listd = devServiceImpl.selectDevInfoByDevid(condition);
        List<DevInfo> listr = new ArrayList<>();
        for (DevInfo devInfo : listd) {
            condition.put("devid", String.valueOf(devInfo.getId()));
            alacount = alarmServiceImpl.selectAlarmCount(condition);
            condition.remove("devid");
            if (devInfo != null) {
                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                    if (alacount > 0) {
                        devInfo.setRemark("报警");
                    } else {
                        devInfo.setRemark("正常");
                    }
                } else {
                    devInfo.setRemark("离线");
                }
                listr.add(devInfo);
            }

        }
        sum = devServiceImpl.selectCountByType(condition);
        ResultData resultData = new ResultData();
        resultData.setData(listr);
        resultData.setCount(sum);
        return JSON.toJSONString(resultData);
    }

    /**
     * 获取监测点列表  树状图1
     */
    @RequestMapping("/devlist")
    private String getdevlist(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer customerId = null;
        User user = JwtUtils.getUser(request);
//        User user = (User) session.getAttribute("user");
        customerId = user.getIcustomerid();
//        condition.put("customerId",customerId.toString());
        Region region = new Region();
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }

        Integer regionId = null;
        List<RegionModel> listrg = regionServiceImpl.selectRegionByCus(customerId);
        List<RegionModel> listrg_re = new ArrayList<RegionModel>();
        for (RegionModel item : listrg
        ) {
            Dev dev = new Dev();
            if (item != null) {
                dev.setIcustomerid(customerId);
                dev.setRegion(item.getId());
                if (condition.get("type") != null) {
                    dev.setType(StringUtils.isBlank(condition.get("type")) ? null : Integer.valueOf(condition.get("type")));
                }

            }
            List<Dev> listdev = devServiceImpl.selectDevByRegion(currentPage, dev);
            item.setDevs(listdev);
            listrg_re.add(item);

        }
        return JSON.toJSONString(listrg_re, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 这个用于参数设置里的树
     *
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping("/devlist2")
    private String getdevlist2(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
//        condition.put("customerId",customerId.toString());
        Region region = new Region();
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }

        Integer regionId = 1;
        List<RegionModel> listrg = regionServiceImpl.selectRegionByCus(icustomerid);
        List<RegionModel> listrg_re = new ArrayList<RegionModel>();
        for (RegionModel item : listrg
        ) {
            Dev dev = new Dev();
            if (item != null) {
                dev.setIcustomerid(icustomerid);
                dev.setRegion(item.getId());
                if (condition.get("type") != null) {
                    dev.setType(StringUtils.isBlank(condition.get("type")) ? null : Integer.valueOf(condition.get("type")));
                }

            }
            List<Dev> listdev = devServiceImpl.selectDevPByRegion(currentPage, dev);
            item.setDevs(listdev);
            listrg_re.add(item);

        }
        return JSON.toJSONString(listrg_re, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 获取设备总数
     */
    @RequestMapping("/devcount")
    private String getdevcount(@RequestParam Map<String, String> condition, HttpServletRequest request) {

        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        Integer type = null;
        if (condition.containsKey("type")) {
            type = StringUtils.isBlank(condition.get("type")) ? 1 : Integer.valueOf(condition.get("type"));
        }
        Integer count = null;

        count = devServiceImpl.selectCountByType(condition);
        ResultData resultData = new ResultData();
        resultData.setCount(count);
        return JSON.toJSONString(resultData, SerializerFeature.DisableCircularReferenceDetect);
    }


    /**
     * 获取风机的列表 或者其他设备的列表
     *
     * @param
     */
    @RequestMapping("/fanlist")
    private String getfanlist(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        Integer type = null;
//         if (condition.containsKey("type")) {
//             type = StringUtils.isBlank(condition.get("type")) ? 1 : Integer.valueOf(condition.get("type"));
//         }
        condition.put("type", "9");
        Integer count = null;
        List<DevInfo> list = devServiceImpl.selectDevInfoByCondition(condition);
        count = devServiceImpl.selectCountByType(condition);
        ResultData resultData = new ResultData();
        if (list.size() > 0) {
            resultData.setDate(DateUtil.dateToString(list.get(list.size() - 1).getUpdateTime()));
        }
        resultData.setData(list);
        resultData.setDevcount(count);
        return JSON.toJSONString(resultData, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 获取设备在线率  首页》》设备在线率
     * 思路：查询每个种类设备的总数
     * 设置online的值为1时的在线设备总数
     * 在线/总设备=设备在线率
     * List(Map<String,Integer>)
     */
    @RequestMapping("/onlinerate")
    private String onlinerate(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> param_tem = new HashMap<String, String>();
        Map<String, String> param_co = new HashMap<String, String>();
        Map<String, String> param_ch4 = new HashMap<String, String>();
        Map<String, String> param_dust = new HashMap<String, String>();
        Map<String, String> param_press = new HashMap<String, String>();
        Map<String, String> param_flow = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat("0.00");
        //红外线温度
        Integer count_tem = null;
        Integer count_tem_online = null;
        String tem_online = "0.00";
        condition.put("type", "2");
        count_tem = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_tem_online = devServiceImpl.selectDevCountByType(condition);
        tem_online = df.format((float) count_tem_online / count_tem);

        condition.remove("online");
        //co
        Integer count_co = null;
        Integer count_co_online = null;
        String co_online = "0.00";
        condition.put("type", "5");
        count_co = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_co_online = devServiceImpl.selectDevCountByType(condition);
        if (count_co_online != null) {
            co_online = df.format((float) count_co_online / count_co);
        }

        //ch4
        Integer count_ch4 = null;
        Integer count_ch4_online = null;
        String ch4_online = "0.00";

        condition.remove("online");
        condition.put("type", "6");
        count_ch4 = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_ch4_online = devServiceImpl.selectDevCountByType(condition);
        if (count_ch4_online != 0) {
            ch4_online = df.format((float) count_ch4_online / count_ch4);
        }
        condition.remove("online");
        //粉尘
        Integer count_dust = null;
        Integer count_dust_online = null;
        String dust_online = "0.00";
        condition.remove("online");
        condition.put("type", "4");
        count_dust = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_dust_online = devServiceImpl.selectDevCountByType(condition);
        if (count_dust_online != 0) {
            dust_online = df.format((float) count_dust_online / count_dust);
        }
        list.add(param_dust);
        condition.remove("online");
        //水压
        Integer count_press = null;
        Integer count_press_online = null;
        String press_online = "0.00";
        condition.put("type", "7");
        count_press = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_press_online = devServiceImpl.selectDevCountByType(condition);
        if (count_press_online != null) {
            press_online = df.format((float) count_press_online / count_press);
        }
        condition.remove("online");
        //流量
        Integer count_flow = null;
        Integer count_flow_online = null;
        String flow_online = "0.00";
        condition.put("type", "8");

        count_flow = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_flow_online = devServiceImpl.selectDevCountByType(condition);
        if (count_flow_online != 0) {
            flow_online = df.format((float) count_flow_online / count_flow);
        }
        OnlineRate onlineRate = new OnlineRate();
        onlineRate.setTemonline(tem_online);
        onlineRate.setCoonline(co_online);
        onlineRate.setCh4online(ch4_online);
        onlineRate.setDustonline(dust_online);
        onlineRate.setPressonline(press_online);
        onlineRate.setFlowonline(flow_online);

        list.add(param_flow);
//        resultData.setCount(count);
        return JSON.toJSONString(onlineRate, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 获取大屏实时数据 三个表格 三个坐标轴
     */
    @RequestMapping("/realtimedata")
    private String realtimedata(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        //获取CO,CH4,粉尘实时数据
        int pos = 0;
        String[] press_region = new String[4];
        double[] press_value = new double[4];
        String[] tem_region = new String[4];
        double[] tem_value = new double[4];
        String[] flow_region = new String[4];
        double[] flow_value = new double[4];

        for (int i = 1; i < 5; i++) {
            press_region[i - 1] = i + "号区域";
            tem_region[i - 1] = i + "号区域";
            flow_region[i - 1] = i + "号区域";
        }


        condition.put("type", "5");
        List<DevInfo> list_co = devServiceImpl.selectCoByCondition(condition);
        condition.put("type", "6");
        List<DevInfo> list_ch4 = devServiceImpl.selectCh4ByCondition(condition);
        condition.put("type", "4");
        List<DevInfo> list_dust = devServiceImpl.selectDustByCondition(condition);
        //获取区域水压温度流量
        //获取水压
        condition.put("type", "7");
        List<DevInfo> list_press = devServiceImpl.selectPressNowByCondition(condition);
//        System.out.println("dstsuigfise"+list_press);
        for (DevInfo item : list_press
        ) {
            pos = Integer.parseInt(item.getRegionname().substring(0, 1)) - 1;
            press_region[pos] = item.getRegionname();
            press_value[pos] = NumUtil.dianhoun(item.getTpressure(),3);
//            pos++;
        }
        //获取区域水流数据
        condition.put("type", "8");
        List<DevInfo> list_flow = devServiceImpl.selectFlowNowByCondition(condition);
        System.out.println("dstsuigfise" + list_flow);
        for (DevInfo item : list_flow
        ) {
            pos = Integer.parseInt(item.getRegionname().substring(0, 1)) - 1;
            flow_region[pos] = item.getRegionname();
            flow_value[pos] = NumUtil.dianhoun(item.getTflow(),3);
//          pos++;
        }
        //获取区域温度
        condition.put("type", "2");
        List<DevInfo> list_tem = devServiceImpl.selectTemNowByCondition(condition);
        for (DevInfo item : list_tem
        ) {
            pos = Integer.parseInt(item.getRegionname().substring(0, 1)) - 1;
            tem_region[pos] = item.getRegionname();
            tem_value[pos] = NumUtil.dianhoun(item.getFT(),3);
//          pos++;
        }
        //获取总设备，在线设备数量，每种在线设备的数量


        ResultData resultData = new ResultData();
        resultData.setData(list_ch4);
        resultData.setDatalst2(list_co);
        resultData.setDatalst3(list_dust);
        resultData.setArrsdata3(tem_region);
        resultData.setArrddata3(tem_value);
        resultData.setArrsdata1(press_region);
        resultData.setArrddata1(press_value);
        resultData.setArrsdatax1(flow_region);
        resultData.setArrddata2(flow_value);
        return JSON.toJSONString(resultData, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/alldevdata")
    private String alldevdata(@RequestParam Map<String, String> condition, HttpServletRequest request) {

        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();

        //获取总设备数量
        int alldev = 0;
        //获取在线设备总数量
        int onlinedev = 0;
        //获取各种设备数量
        condition.remove("type");
        alldev = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        onlinedev = devServiceImpl.selectDevCountByType(condition);


        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> param_tem = new HashMap<String, String>();
        Map<String, String> param_co = new HashMap<String, String>();
        Map<String, String> param_ch4 = new HashMap<String, String>();
        Map<String, String> param_dust = new HashMap<String, String>();
        Map<String, String> param_press = new HashMap<String, String>();
        Map<String, String> param_flow = new HashMap<String, String>();
        DecimalFormat df = new DecimalFormat("0");
        //红外线温度
        Integer count_tem = null;
        Integer count_tem_online = null;
        String tem_online = "0";
        condition.put("type", "2");
        count_tem = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_tem_online = devServiceImpl.selectDevCountByType(condition);
        tem_online = df.format((float) count_tem_online / count_tem * 100);

        condition.remove("online");
        //co
        Integer count_co = null;
        Integer count_co_online = null;
        String co_online = "0";
        condition.put("type", "5");
        count_co = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_co_online = devServiceImpl.selectDevCountByType(condition);
        if (count_co_online != null) {
            co_online = df.format((float) count_co_online / count_co * 100);
        }

        //ch4
        Integer count_ch4 = null;
        Integer count_ch4_online = null;
        String ch4_online = "0";

        condition.remove("online");
        condition.put("type", "6");
        count_ch4 = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_ch4_online = devServiceImpl.selectDevCountByType(condition);
        if (count_ch4_online != 0) {
            ch4_online = df.format((float) count_ch4_online / count_ch4 * 100);
        }
        condition.remove("online");
        //粉尘
        Integer count_dust = null;
        Integer count_dust_online = null;
        String dust_online = "0";
        condition.remove("online");
        condition.put("type", "4");
        count_dust = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_dust_online = devServiceImpl.selectDevCountByType(condition);
        if (count_dust_online != 0) {
            dust_online = df.format((float) count_dust_online / count_dust * 100);
        }
        list.add(param_dust);
        condition.remove("online");
        //水压
        Integer count_press = null;
        Integer count_press_online = null;
        String press_online = "0";
        condition.put("type", "7");
        count_press = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_press_online = devServiceImpl.selectDevCountByType(condition);
        if (count_press_online != null) {
            dust_online = df.format((float) count_press_online / count_press * 100);
        }
        //流量
        Integer count_flow = null;
        Integer count_flow_online = null;
        String flow_online = "0";
        condition.put("type", "8");
        count_flow = devServiceImpl.selectDevCountByType(condition);
        condition.put("online", "1");
        count_flow_online = devServiceImpl.selectDevCountByType(condition);
        if (count_flow_online != 0) {
            flow_online = df.format((float) count_flow_online / count_flow * 100);
        }
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();
        Map<String, String> map4 = new HashMap<>();
        Map<String, String> map5 = new HashMap<>();
        Map<String, String> map6 = new HashMap<>();
        map.put("name", "温度传感器");
        map.put("value", tem_online);
        map2.put("name", "CO检测传感器");
        map2.put("value", co_online);
        map3.put("name", "CH4检测传感器");
        map3.put("value", ch4_online);
        map4.put("name", "粉尘浓度传感器");
        map4.put("value", dust_online);
        map5.put("name", "水压检测仪");
        map5.put("value", press_online);
        map6.put("name", "水流检测仪");
        map6.put("value", flow_online);
        mapList.add(map);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);
        mapList.add(map5);
        mapList.add(map6);


        OnlineRate onlineRate = new OnlineRate();
        onlineRate.setDevcount(String.valueOf(alldev));
        onlineRate.setDevonlinecount(String.valueOf(onlinedev));
        List<DevInfo> lst = new ArrayList<>();
        DevInfo devInfo = new DevInfo();
        if (mapList.size() > 0) {
            devInfo.setMapList(mapList);
        }

        devInfo.setDevcount(alldev);
        devInfo.setDevcountonline(onlinedev);


        return JSON.toJSONString(devInfo, SerializerFeature.DisableCircularReferenceDetect);
    }
}
