package com.internet.cms.basic.util;

import freemarker.template.Configuration;

public class FreemarkerUtil {

	private static FreemarkerUtil util;
	private static Configuration cfg;

	public static FreemarkerUtil getInstance(String ftlPath) {
		if(util == null){
			cfg = new Configuration();
			cfg.setClassForTemplateLoading(FreemarkerUtil.class, ftlPath);
			cfg.setDefaultEncoding("utf-8");
			util = new FreemarkerUtil();
		}
		return util;
	}

}
