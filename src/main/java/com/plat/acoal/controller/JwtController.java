package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.User;
import com.plat.acoal.service.impl.UserServiceImpl;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.JwtUtils;
import com.plat.acoal.utils.MD5Utils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-04-26 08:54
 * @des ：JWT的Controller
 **/
@RestController
@RequestMapping(value = "/jwt", produces = "application/json;charset=UTF-8")
public class JwtController {
    @Autowired
    UserServiceImpl usi;

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> condition, HttpServletRequest res) {
        System.out.println("/jwt/login::::" + JSON.toJSONString(condition));
        String token ;
        JsonResult jr = new JsonResult();
        if (!condition.containsKey("username") || !condition.containsKey("password")) {
            jr.setStatus(500);
            return JSON.toJSONString(jr);
        }
        User user = usi.selectUserByUsername(condition.get("username"));
        if (user.getCpassword().equals(MD5Utils.md5(condition.get("password")))) {
            JwtBuilder builder = Jwts.builder();
            Map<String, Object> claims = new HashMap<>();
            claims.put("user", user);
            token = builder.setSubject(user.getCusername()).setClaims(claims).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            System.out.println(token);
        } else {
            jr.setStatus(500);
            return JSON.toJSONString(jr);
        }
        jr.setStatus(200);
        user.setToken(token);
        user.setCpassword(null);
        jr.setData(user);
        return JSON.toJSONString(jr);
    }
}
