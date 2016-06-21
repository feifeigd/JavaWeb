package com.internet.cms.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.internet.cms.model.BaseInfo;

public class BaseInfoUtil {
	private static BaseInfoUtil biu;
	private static Properties prop;
	
	private BaseInfoUtil()throws IOException{
		if (prop == null ){
			prop = new Properties();
			prop.load(new InputStreamReader(BaseInfoUtil.class.getClassLoader().getResourceAsStream("baseinfo.properties"), "utf-8"));
		}
	}
	
	public static BaseInfoUtil getInstance() {
		try{
			if (biu == null )biu = new BaseInfoUtil();
			return biu;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public BaseInfo read() {
		BaseInfo bi = new BaseInfo();
		
		bi.setAddress(prop.getProperty("address"));
		bi.setEmail(prop.getProperty("email"));
		bi.setName(prop.getProperty("name"));
		bi.setPhone(prop.getProperty("phone"));
		bi.setRecordCode(prop.getProperty("recordCode"));
		bi.setZipCode(prop.getProperty("zipCode"));
		bi.setDomainName(prop.getProperty("domainName"));
		bi.setIndexPicNumber(Integer.parseInt(prop.getProperty("indexPicNumber")));
		String w = prop.getProperty("indexPicSize");
		String[] ws = w.split("\\*");
		bi.setIndexPicWith(Integer.parseInt(ws[0]));
		bi.setIndexPicHeight(Integer.parseInt(ws[1]));
		
		return bi;
	}

}
