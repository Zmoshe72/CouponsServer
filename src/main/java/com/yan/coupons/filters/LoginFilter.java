package com.yan.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yan.coupons.dto.UserLoginData;
import com.yan.coupons.logic.CacheController;

@Component
public class LoginFilter implements Filter{
	
	@Autowired
	CacheController cacheController;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=  (HttpServletRequest) request;
		String pageRequest=req.getRequestURL().toString();

		if(pageRequest.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}

		if (pageRequest.endsWith("/user") && req.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}

		String token = req.getHeader("Authorization");
		UserLoginData userLoginData=  (UserLoginData)cacheController.get(token);
		if (userLoginData != null) {
			request.setAttribute("userLoginData", userLoginData);
			chain.doFilter(request, response);
			return;
		}
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(401);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
