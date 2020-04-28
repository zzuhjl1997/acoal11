package com.plat.acoal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "最近四天的报警数据")
@Data
public class AlarmFourModel {
    @ApiModelProperty(value = "报警时间")
    private String alarmTime;
    @ApiModelProperty(value="报警值")
    private Integer value;
}
