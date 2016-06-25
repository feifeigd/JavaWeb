package com.internet.cms.web;

/*import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;*/
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.internet.cms.auth.AuthUtil;
//import com.internet.cms.controller.LoginController;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310721741142136774L;
	
	private static WebApplicationContext wc;	

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 初始化spring的工厂
		ServletContext sc = getServletContext();
		wc = WebApplicationContextUtils.getWebApplicationContext(sc);
		// 初始化权限信息
		Map<String, Set<String> > auths = AuthUtil.initAuth("com.internet.cms.controller");	// 搞控制器这个包里面的
		sc.setAttribute("allAuths", auths);
		sc.setAttribute("baseInfo", BaseInfoUtil.getInstance().read());
		System.out.println("--- 系统初始化成功: "+ auths + "---");
		
		/*BeanInfo info;
		try {
			info = Introspector.getBeanInfo(LoginController.class);
	        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {  
	            System.out.println(pd.getName());  
	        }  
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}*/  
	}

	public static WebApplicationContext getWc(){
		return wc;
	}
}
