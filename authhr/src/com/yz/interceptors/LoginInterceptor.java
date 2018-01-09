package com.yz.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yz.entity.User;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURI();
		
		if(url.contains("login")){
			return true ;
		}
		
		//2、判断用户是否登录
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			//请求转发
			request.getRequestDispatcher("/login").forward(request, response);
			//重定向到登陆页面
			//response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}

}
