package com.code.mallservice.mall.config;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.utils.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        if(StringUtils.isEmpty(token)){
//            token = request.getParameter("token");
//        }
//        if(StringUtils.isEmpty(token)){
//            responseMsg(response);
//            return false;
//        }
//        //用户解析
//        FbAccountKeeper accountKeeper = JwtUtils.getAccountByToken(token);
//        if(accountKeeper == null){
//            responseMsg(response);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void responseMsg(HttpServletResponse response) throws Exception{
        PrintWriter printWriter = response.getWriter();
        String json = JSON.toJSONString(Result.loginOut());
        printWriter.write(json);
        printWriter.close();
    }
}
