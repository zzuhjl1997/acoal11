package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;
@Data
public class OperationIAO {
    /**
     * @ 用于查询操作日志传进去和输出来的参数
     */
    private Date operationdate;
    private String username;
    private String dcollectstart;
    private String dcollectend;
    private Integer status;
    private String taction;
    private String turl;
    private String turlname;
    private Integer count;


}
