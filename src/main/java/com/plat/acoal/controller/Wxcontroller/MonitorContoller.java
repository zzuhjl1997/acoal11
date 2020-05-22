package com.plat.acoal.controller.Wxcontroller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.Dust;
import com.plat.acoal.entity.Temperature;
import com.plat.acoal.model.*;
import com.plat.acoal.service.impl.*;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Log4j2
@RequestMapping(value = "/monitor", produces = "application/json;charset=UTF-8")
public class MonitorContoller {
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    public AlarmServiceImpl alarmServiceImpl;
    @Autowired
    public ParameterServiceImpl parameterServiceImpl;
    @Autowired
    public TemperatureServiceImpl temperatureServiceImpl;
    @Autowired
    public DustServiceImpl dustServiceImpl;
    @Autowired
    public GasServiceImpl gasServiceImpl;
    @Autowired
    public PressureFlowServiceImpl pressureFlowServiceImpl;
    @Autowired
    public CannonServiceImpl cannonServiceImpl;

    /**
     * 设备监测
     *
     * @param condition
     * @param session
     * @return
     */
    @RequestMapping(value = "/devlist")
    private String devlist(@RequestParam Map<String, String> condition, HttpSession session) {
        //type
        List<DevInfo> list = devServiceImpl.selectDevList(condition);
        List<DevInfo> listr = new ArrayList<>();
        List<DevInfo> list_re;
        List<ParameterInfo> listp;
        //查询基础信息
        for (DevInfo devInfo : list) {
            condition.put("devid", String.valueOf(devInfo.getId()));
            //0=离线 1=正常 2=报警
            if (devInfo != null) {
                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                    if (devInfo.getType() != null) {
                        condition.put("type", String.valueOf(devInfo.getType()));
                        switch (devInfo.getType()) {
                            case 2:
                                TemperatureInfo temperatureInfo = new TemperatureInfo();
                                temperatureInfo.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<TemperatureInfo> listft = temperatureServiceImpl.selectNewFtById(temperatureInfo);
                                if (listft.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (TemperatureInfo temitem : listft) {
                                                if (temitem.getFt() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }

                                        }
                                    } else {
                                        condition.put("devid", devInfo.getId().toString());
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (TemperatureInfo temitem : listft) {
                                                if (temitem.getFt() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }

                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }

                                break;
                            case 4:
                                DustModel dustModel = new DustModel();
                                dustModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<DustModel> listd = dustServiceImpl.selectNewInfoById(dustModel);
                                if (listd.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (DustModel dustitem : listd) {
                                                if (dustitem.getFdust() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }

                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (DustModel dustitem : listd) {
                                                if (dustitem.getFdust() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }

                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 5:
                                GasModel gasModel = new GasModel();
                                gasModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<GasModel> listco = gasServiceImpl.selectNewCoById(gasModel);
                                if (listco.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listco) {
                                                if (coitem.getGco() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listco) {
                                                if (coitem.getGco() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }


                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 6:
                                gasModel = new GasModel();
                                gasModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<GasModel> listch4 = gasServiceImpl.selectNewCh4ById(gasModel);
                                if (listch4.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listch4) {
                                                if (coitem.getGch4() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listch4) {
                                                if (coitem.getGch4() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }


                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 7:
                                PressureFlowModel pressureFlowModel = new PressureFlowModel();
                                pressureFlowModel.setPressureid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<PressureFlowModel> listpf = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
                                if (listpf.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listpf) {
                                                if (pfModel.getTpressure() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listpf) {
                                                if (pfModel.getTpressure() != null && pfModel.getTpressure() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;

                            case 8:
                                pressureFlowModel = new PressureFlowModel();
                                pressureFlowModel.setFlowid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                List<PressureFlowModel> listf = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
                                if (listf.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listf) {
                                                if (pfModel.getTflow() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }
                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listf) {
                                                if (pfModel.getTflow() != null && pfModel.getTflow() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");

                                }
                                break;

                            case 3:
                                condition.remove("type");

                                condition.put("devid", devInfo.getId().toString());
                                List<CannonInfo> listc = cannonServiceImpl.selectNewCannonById(condition);
                                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                                    for (CannonInfo cannonInfo : listc) {

                                        if (cannonInfo.getIsfire() == 1) {
                                            devInfo.setRemark("报警");
                                        } else if (cannonInfo.getIsfault() == 1) {
                                            devInfo.setRemark("故障");
                                        } else {
                                            devInfo.setRemark("正常");
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("离线");
                                }
                                break;
                            default:
                                break;
                        }
                    }

                } else if (devInfo.getOnline() != null && devInfo.getOnline() == 0) {
                    devInfo.setRemark("离线");
                }
            }
            listr.add(devInfo);
        }


        condition.remove("devid");
        condition.remove("type");

        //正常1 离线0 报警2 3无数据

        if (condition.containsKey("status")) {
            switch (condition.get("status")) {
                case "0":
                    list_re = listr.stream().filter(devInfo -> devInfo.getRemark() != null && devInfo.getRemark().equals("离线")).collect(Collectors.toList());
                    break;

                case "1":
                    list_re = listr.stream().filter(devInfo -> devInfo.getRemark() != null && devInfo.getRemark().equals("正常")).collect(Collectors.toList());
                    break;
                case "2":
                    list_re = listr.stream().filter(devInfo -> devInfo.getRemark() != null && devInfo.getRemark().equals("报警")).collect(Collectors.toList());
                    break;
                case "3":
                    list_re = listr.stream().filter(devInfo -> devInfo.getRemark() != null && devInfo.getRemark().equals("无数据")).collect(Collectors.toList());
                    break;
                default:
                    list_re = listr;

            }
        } else {
            list_re = listr;

        }

        return JSON.toJSONString(list_re);
    }

    @RequestMapping(value = "alldev")
    public String alldev(@RequestParam Map<String, String> condition, HttpSession session) {
        List<DevInfo> list = new ArrayList<>();
        list = devServiceImpl.selectDevList(condition);
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "devinfo")
    public String devinfo(@RequestParam Map<String, String> condition, HttpSession session) {
        //type
        List<DevInfo> list = devServiceImpl.selectDevList(condition);
        List<ParameterInfo> listp;
        List<DevInfo> listr = new ArrayList<>();

        for (DevInfo devInfo : list) {
            condition.put("devid", String.valueOf(devInfo.getId()));
            //0=离线 1=正常 2=报警
            if (devInfo != null) {
                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                    if (devInfo.getType() != null) {
                        condition.put("type", String.valueOf(devInfo.getType()));
                        switch (devInfo.getType()) {
                            case 2:
                                TemperatureInfo temperatureInfo = new TemperatureInfo();
                                temperatureInfo.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<TemperatureInfo> listft = temperatureServiceImpl.selectNewFtById(temperatureInfo);
                                if (listft.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (TemperatureInfo temitem : listft) {
                                                if (temitem.getFt() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("温度过高");
                                                    devInfo.setFT(temitem.getFt());
                                                } else {
                                                    devInfo.setRemark("");
                                                }

                                            }

                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (TemperatureInfo temitem : listft) {
                                                if (temitem.getFt() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("温度过高");
                                                    devInfo.setFT(temitem.getFt());
                                                } else {
                                                    devInfo.setRemark("");

                                                }

                                            }

                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }

                                break;
                            case 4:
                                DustModel dustModel = new DustModel();
                                dustModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<DustModel> listd = dustServiceImpl.selectNewInfoById(dustModel);
                                if (listd.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (DustModel dustitem : listd) {
                                                if (dustitem.getFdust() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }

                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (DustModel dustitem : listd) {
                                                if (dustitem.getFdust() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }

                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 5:
                                GasModel gasModel = new GasModel();
                                gasModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<GasModel> listco = gasServiceImpl.selectNewCoById(gasModel);
                                if (listco.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listco) {
                                                if (coitem.getGco() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listco) {
                                                if (coitem.getGco() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }


                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 6:
                                gasModel = new GasModel();
                                gasModel.setDevid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<GasModel> listch4 = gasServiceImpl.selectNewCh4ById(gasModel);
                                if (listch4.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listch4) {
                                                if (coitem.getGch4() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                        for (ParameterInfo parameterInfo : listp) {
                                            for (GasModel coitem : listch4) {
                                                if (coitem.getGch4() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }


                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;
                            case 7:
                                PressureFlowModel pressureFlowModel = new PressureFlowModel();
                                pressureFlowModel.setPressureid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                List<PressureFlowModel> listpf = pressureFlowServiceImpl.selectNewPById(pressureFlowModel);
                                if (listpf.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listpf) {
                                                if (pfModel.getTpressure() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listpf) {
                                                if (pfModel.getTpressure() != null && pfModel.getTpressure() >= Double.valueOf(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");
                                }
                                break;

                            case 8:
                                pressureFlowModel = new PressureFlowModel();
                                pressureFlowModel.setFlowid(devInfo.getId());
                                listp = parameterServiceImpl.selectParamInfoByCondition(condition);

                                List<PressureFlowModel> listf = pressureFlowServiceImpl.selectNewFById(pressureFlowModel);
                                if (listf.size() > 0) {
                                    if (listp.size() > 0) {
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listf) {
                                                if (pfModel.getTflow() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }
                                            }
                                        }
                                    } else {
                                        condition.put("devid", "0");
                                        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
                                        for (ParameterInfo parameterInfo : listp) {
                                            for (PressureFlowModel pfModel : listf) {
                                                if (pfModel.getTflow() != null && pfModel.getTflow() >= Double.parseDouble(parameterInfo.getCvalue())) {
                                                    devInfo.setRemark("报警");
                                                } else {
                                                    devInfo.setRemark("正常");
                                                }

                                            }
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("无数据");

                                }
                                break;

                            case 3:
                                condition.remove("type");

                                condition.put("devid", devInfo.getId().toString());
                                List<CannonInfo> listc = cannonServiceImpl.selectNewCannonById(condition);
                                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                                    for (CannonInfo cannonInfo : listc) {

                                        if (cannonInfo.getIsfire() == 1) {
                                            devInfo.setRemark("报警");
                                        } else if (cannonInfo.getIsfault() == 1) {
                                            devInfo.setRemark("故障");
                                        } else {
                                            devInfo.setRemark("正常");
                                        }

                                    }
                                } else {
                                    devInfo.setRemark("离线");
                                }
                                break;
                            default:
                                break;
                        }
                    }

                } else if (devInfo.getOnline() != null && devInfo.getOnline() == 0) {
                    devInfo.setRemark("离线");
                }
            }

            listr.add(devInfo);
        }

        return JSON.toJSONString(listr);
    }


    @RequestMapping(value = "historyinfo")
    public String historyinfo(@RequestParam Map<String, String> condition, HttpSession session) {


        return null;
    }


}
