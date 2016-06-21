package com.internet.cms.web;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.internet.cms.auth.AuthUtil;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310721741142136774L;
	
	private static WebApplicationContext wc;

	

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// 初始化spring的工厂
		ServletContext sc = getServletContext();
		wc = WebApplicationContextUtils.getWebApplicationContext(sc);
		// 初始化权限信息
		Map<String, Set<String> > auths = AuthUtil.initAuth("com.initernet.cms.controller");
		sc.setAttribute("allAuths", auths);
		sc.setAttribute("baseInfo", BaseInfoUtil.getInstance().read());
		System.out.println("--- 系统初始化成功: "+ auths + "---");
	}



	public static WebApplicationContext getWc(){
		return wc;
	}
}
