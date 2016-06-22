package com.internet.cms.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.internet.cms.page.SystemContext;

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
		Integer offset = 0;
		try{
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		}catch(NumberFormatException e){}
		
		try{
			SystemContext.setOrder(request.getParameter("order"));
			SystemContext.setSort(request.getParameter("sort"));
			SystemContext.setPageOffset(offset);
			SystemContext.setPageSize(pageSize);
			SystemContext.setRealPath(((HttpServletRequest)request).getSession().getServletContext().getRealPath("/"));
			chain.doFilter(request, response);
		}finally{
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
			SystemContext.removeRealPath();
		}		
	}
	
	public void destroy() {
		
	}

}
