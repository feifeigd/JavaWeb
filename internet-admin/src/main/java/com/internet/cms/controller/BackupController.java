package com.internet.cms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.internet.cms.auth.AuthClass;
import com.internet.cms.basic.util.BackupFileUtil;
import com.internet.cms.page.SystemContext;
import com.internet.cms.service.IIndexService;

@AuthClass
@Controller
@RequestMapping("/admin")
public class BackupController {
	
	@Inject
	private IIndexService indexService;

	@RequestMapping(value="/backup/add", method=RequestMethod.GET)
	public String backup(){
		return "backup/add";
	}
	
	@RequestMapping(value="backups")
	public String lisg(Model model){
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		model.addAttribute("backups", bfu.listBackups());
		return "backup/list";
	}
	
	@RequestMapping("resume/{name}")
	public String resume(@PathVariable String name, String type){
		BackupFileUtil bfu = BackupFileUtil.getInstance(SystemContext.getRealPath());
		bfu.resume(name + "." + type);
		indexService.generateTop();
		return "redirect:/admin/backups";
	}
}
