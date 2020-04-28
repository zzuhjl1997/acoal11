package com.plat.acoal.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AlarmAmountModel {
    @ApiModelProperty(value = "报警总数")
    private Integer value;
}
