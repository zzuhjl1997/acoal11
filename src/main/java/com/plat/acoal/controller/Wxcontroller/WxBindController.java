package com.plat.acoal.controller.Wxcontroller;


import com.plat.acoal.utils.GetGZHOpenId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Locale;


@RestController
@RequestMapping(value = "/acbind")
public class WxBindController {
    private static Logger logger = LoggerFactory.getLogger(WxBindController.class);

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws ParseException
     * @throws URISyntaxException
     * @throws SQLException
     */
    @RequestMapping(value = "/getopenid", produces = "application/json;charset=utf-8") // ,method =
    /**
     *
     */
    private String getopenid(HttpServletRequest request, HttpServletResponse response, Locale locale)
            throws ParseException, IOException, ParseException, URISyntaxException, SQLException {
        logger.info("WxBindController", "getopenid");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的域都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        String code = "";

        if (request.getParameter("code") != null && !"".equals(request.getParameter("code"))) {
            code = (String) (request.getParameter("code"));
        }
        // 获取到openId
        String openId = GetGZHOpenId.getopendid(code);
        // 把openid到session
        HttpSession session = request.getSession();
        session.setAttribute("openid", openId);
        String url = "http://www.lanwu114.club";
        StringBuffer url_code = new StringBuffer(url);
        // 这里请不要使用get请求单纯的将页面跳转到该url即可
        response.sendRedirect(url_code.toString());
        logger.info(url_code.toString(),"url_code.toString()");
        return null;
    }
}
