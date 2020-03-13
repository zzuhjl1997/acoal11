package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.UserCustomer;
import com.plat.acoal.service.UserService;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
import com.plat.acoal.service.impl.UserServiceImpl;
import com.plat.acoal.utils.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@RestController
 *reestful风格的Controller的注解，用之替代Controller;相当于@Controller+@ResponseBody
 */
@RestController
@Log4j2
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    public UserServiceImpl usi;
    @Autowired
    public OperationLogServiceImpl osi;

    @GetMapping("")
    public String selectAllUserCus() {
        int sequence=0;
        User user = new User();
        List<UserCustomer> list = usi.selectAllUserCus(user);
        List<UserCustomer> list_re = new ArrayList<UserCustomer>();
        for (UserCustomer uc:list
             ) {
            sequence ++;
            uc.setSequence(sequence);
            list_re.add(uc);
        }

        return JSON.toJSONString(list_re);
    }

    /*
     *@GetMapping
     *相当于@RequestMapping(value = "",method = RequestMethod.GET)
     */
 /*   @GetMapping("/{id}")
    public String selectUserById(@PathVariable("id") Integer id) {

        User user = usi.selectUserById(id);
        return JSON.toJSONString(user);
    }*/
    @GetMapping("/search")
    public String selectUserByName(String cusername) {

        List<UserCustomer> usercus_re = usi.selectUserByName(cusername);
        for (UserCustomer uc : usercus_re
        ) {
            uc.setRemark("");
        }

        return JSON.toJSONString(usercus_re);
    }

    /**
     * hjl
     * 添加用户并进行日志记录
     *
     * @param user
     * @param request
     * @return json
     */
    @PostMapping("/add")
    public String addUser(User user, HttpServletRequest request) {

//        User user_s= (User) request.getSession().getAttribute("");
//        int userid=user.getIuserid();
        OperationLog operationLog = new OperationLog();

        int i = usi.addUser(user);
        JsonResult jr = new JsonResult();
        if (i > 0) {
            jr.setStatus(200);
            jr.setMsg("添加成功");
        }
        int userid = 517704512;
        String uri = request.getRequestURI();
        String action = request.getMethod();

        operationLog.setOperationdate(new Date());
        operationLog.setOperationuserid(userid);
        operationLog.setTaction(action);
        operationLog.setTurl(uri);
        operationLog.setTurlname("增加用户");
        operationLog.setStatus(0);
        int j = osi.addLogs(operationLog);
        if (i > 0) {
            operationLog.setStatus(1);
            jr.setStatus(200);
            jr.setMsg("添加用户成功");
        }

        return JSON.toJSONString(jr);
    }

    @PostMapping("/update")
    public String updateUser(User user, HttpServletRequest request) {

        int i = usi.updateUser(user);
        JsonResult jr = new JsonResult();

        int userid = 517704512;
        String uri = request.getRequestURI();
        String action = request.getMethod();
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationdate(new Date());
        operationLog.setOperationuserid(userid);
        operationLog.setTaction(action);
        operationLog.setTurl(uri);
        operationLog.setTurlname("修改用户");

        operationLog.setStatus(0);
        if (i > 0) {
            operationLog.setStatus(1);
            jr.setStatus(200);
            jr.setMsg("更改成功");
        }
        int j = osi.addLogs(operationLog);
        if (j > 0) {
            operationLog.setStatus(1);
//            jr.setStatus(200);
//            jr.setMsg("修改日志成功");
        }

        return JSON.toJSONString(jr);
    }


    /*
     *在这个@DeleteMapping注解中的{id}代表地址参数
     * 比如访问地址/user/123，{id}即为123
     * 访问地址/user/123456，{id}就是23456
     * 乍然一看，地址不同，但是实际都是调用同一个方法
     * 变体还有/user/{id}/child等，殊途同归
     * 获取该参数的注解为@PathVariable(value="XX")，其中XX要和地址{XX}中的XX相同
     */
    @PostMapping("/delete")
    public String deleteUserById(Integer id, HttpServletRequest request) {

        int i = usi.deleteUserById(id);
        JsonResult jr = new JsonResult();
        if (i > 0) {
            jr.setStatus(200);
            jr.setMsg("删除成功");
        }
        int userid = 517704512;
        String uri = request.getRequestURI();
        String action = request.getMethod();
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationdate(new Date());
        operationLog.setOperationuserid(userid);
        operationLog.setTaction(action);
        operationLog.setTurl(uri);
        operationLog.setTurlname("删除用户");

        operationLog.setStatus(0);
        if (i > 0) {
            operationLog.setStatus(1);
            jr.setStatus(200);
            jr.setMsg("删除成功");
        }
        int j = osi.addLogs(operationLog);

        return JSON.toJSONString(jr);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    private JsonResult login(String username,String password,HttpServletRequest request){

        JsonResult jr = usi.selectUserByUserName(username,password);
        if (jr.getStatus().equals(200)){
            request.getSession().setAttribute("user",jr.getData());
        }
        return jr;
    }

}