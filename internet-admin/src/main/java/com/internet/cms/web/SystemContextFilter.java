package com.internet.cms.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SystemContextFilter implements Filter {

	private Integer pageSize;

	public void init(FilterConfig filterConfig) throws ServletException {
		try{
			pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
		}catch(NumberFormatException e){
			pageSize = 10;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Integer offset = 0;
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch(NumberFormatException e){}
		
		try{
			//SystemContext.
		}finally{
			
		}
		
	}
	
	public void destroy() {
		
	}

}
