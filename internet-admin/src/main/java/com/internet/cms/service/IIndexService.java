package com.internet.cms.service;

/// 重新生成顶部导航（获取所有的导航栏目，栏目的状态必须为已经弃用）
public interface IIndexService {

	public void generateTop();
	
	public void generateBody();
	
	public void generateBottom();
}
