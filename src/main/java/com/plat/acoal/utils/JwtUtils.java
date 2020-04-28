package com.plat.acoal.utils;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.User;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-04-26 17:28
 * @des ：
 **/
public class JwtUtils {
    public static User getUser(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("claims:::"+claims.get("user").getClass()+":::"+JSON.toJSON(claims.get("user")));
        List<User> user = JSON.parseArray("["+JSON.toJSONString(claims.get("user"))+"]",User.class);
        if(user!=null){
            return user.get(0);
        }
        return null;
    }
}
