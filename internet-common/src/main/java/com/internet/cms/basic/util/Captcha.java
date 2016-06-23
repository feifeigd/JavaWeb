package com.internet.cms.basic.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {

	private static Captcha captcha;
	private String code;
	private int num;
	private int width;
	private int height;
	private static final Random ran = new Random();

	private Captcha(){
		code = "0123456789";
		num = 4;
	}
	
	public static Captcha getInstance() {
		if(captcha == null)captcha = new Captcha();
		return captcha;
	}

	public void set(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public String generateCheckcode() {
		StringBuffer cc = new StringBuffer();
		for(int i = 0; i < num; ++i)
			cc.append(code.charAt(ran.nextInt(code.length())));
		return cc.toString();
	}

	public BufferedImage generateCheckImg(String checkcode) {
		// 创建一个图片对象
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图片对象的画笔
		Graphics2D graphics = img.createGraphics();
		// 填充白色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,  0, width, height);
		// 画边框
		graphics.setColor(Color.BLACK);
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		Font font = new Font("宋体", Font.BOLD + Font.ITALIC, (int)(height * .8));
		graphics.setFont(font);
		for(int i = 0; i < num; ++i){
			graphics.setColor(new Color(ran.nextInt(180), ran.nextInt(180), ran.nextInt(180))); // 每次不同颜色
			graphics.drawString(String.valueOf(checkcode.charAt(i)), i * (width / num) + 4, (int)(height * .8));
		}
		
		// 加一些点
		for(int i = 0; i < (width + height); ++i){
			graphics.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255))); // 每次不同颜色
			graphics.drawOval(ran.nextInt(width), ran.nextInt(height), 2, 1);			
		}
		
		// 加一些线
		for(int i = 0; i < 4; ++i){
			graphics.setColor(new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255))); // 每次不同颜色
			graphics.drawLine(0, ran.nextInt(height), width, ran.nextInt(height));
		}
		return img;
	}
	
}
