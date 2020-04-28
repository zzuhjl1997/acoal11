package com.plat.acoal.model;

import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class AlarmStatisticsModel {
    //设备类型ID
    private Integer typeId;
    //设备类型名字
    private String typeName;
    //横坐标，即报警时间
    private List<String> xaxis;
    //纵坐标，即该时间下，该设备类型的报警总数
    private List<String> yaxis;
    private List<YaxisModel> tempY;
}
