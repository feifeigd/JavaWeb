package com.internet.cms.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.internet.cms.basic.util.Captcha;

@Controller
public class LoginController {
	
	/// 请求 /login.do
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		System.out.println("LoginController.login");
		return "admin/login";
	}
	
	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp, HttpSession session)throws IOException{
		resp.setContentType("image/jpg");
		int width = 200;
		int height = 30;
		Captcha c = Captcha.getInstance();
		c.set(width, height);
		String checkcode = c.generateCheckcode();
		session.setAttribute("cc", checkcode);
		OutputStream os = resp.getOutputStream();
		ImageIO.write(c.generateCheckImg(checkcode), "jpg", os);
	}
}
