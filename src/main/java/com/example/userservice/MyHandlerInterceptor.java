package com.example.userservice;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {

        //response.addHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


//        //here check headers contained in request object
//        if (request.getHeader("header1") == null || request.getHeader("header2") == null) {
//            response.getWriter().write("something");
//            response.setStatus(someErrorCode);
//
//            return false;
//        }
//
//        response.getWriter().write("Invalid Request Source");
//        response.setStatus(502);
//        System.out.println("Interceptor preHandleCalled");
//        return false;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Content-Type", "application/json");
        return true;
    }
}