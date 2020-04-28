package com.plat.acoal.filter;

import com.plat.acoal.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-04-26 08:56
 * @des ：此类声明了一个JWT过滤器类，从http请求中提取JWT的信息，并且对JWT进行验证
 * @desBy ：LiuYang | Leon Willow
 **/
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request,response);
        }else{
            if(authHeader == null || !authHeader.startsWith("Bearer ")){
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token = authHeader.substring(7);
            try{
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
                request.setAttribute("claims",claims);
            }catch (final SignatureException e){
                throw new ServletException("Invalid token");
            }
            chain.doFilter(request,response);
        }
    }
}
