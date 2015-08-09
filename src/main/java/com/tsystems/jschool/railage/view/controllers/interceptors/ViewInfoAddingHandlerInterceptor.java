package com.tsystems.jschool.railage.view.controllers.interceptors;

import com.tsystems.jschool.railage.view.Utils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by rudolph on 09.08.15.
 */
public class ViewInfoAddingHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null){
           String viewName = modelAndView.getViewName();
           if(viewName != null){
               if(modelAndView.getModel() != null){
                   modelAndView.getModel().put(Utils.SPRING_VIEW_NAME,viewName);
               }
           }
        }
    }
}
