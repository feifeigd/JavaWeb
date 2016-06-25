package com.internet.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internet.cms.basic.util.FreemarkerUtil;


@Service("indexService")
public class IndexService implements IIndexService {

	private FreemarkerUtil util;
	private String outPath;

	/*@Autowired(required = true)
	public IndexService(String ftlPath, String outPath){
		if(util == null){
			this.outPath = outPath;
			util = FreemarkerUtil.getInstance(ftlPath);
		}
	}*/
	
	@Override
	public void generateTop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateBody() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateBottom() {
		// TODO Auto-generated method stub

	}

}
