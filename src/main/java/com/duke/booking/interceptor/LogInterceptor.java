package com.duke.booking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LogInterceptor implements HandlerInterceptor  {
	  private Logger logger = LoggerFactory.getLogger(this.getClass());

	  @Override
	  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	      throws Exception {

	    logger.debug("Method : {} / URL : {} / IP : {}", request.getMethod(), request.getRequestURL(),
	        request.getRemoteAddr());
	    logger.debug("{} called.", handler.toString());
	    return true;
	  }

	  @Override
	  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	      ModelAndView modelAndView) throws Exception {

	    if (modelAndView != null) {
	      logger.debug("{} exited. {} using.", handler.toString(), modelAndView.getViewName());
	    } else {
	      logger.debug("{} exited.", handler.toString());
	    }
	  }
}
