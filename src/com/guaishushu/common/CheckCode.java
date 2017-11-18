package com.guaishushu.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckCode extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 7.禁止浏览器缓存随机图片
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		// 6.通知客户端以图片的形式打开发送过去的数据
		response.setHeader("Content-Type", "image/jpeg");

		// 1.在内存中创建一张图片
		BufferedImage image = new BufferedImage(70, 30,
				BufferedImage.TYPE_INT_BGR);

		// 2.向图片上写数据
		Graphics g = image.getGraphics();

		// 设置背景色
		g.setColor(Color.decode("#66c67f"));
		g.fillRect(0, 0, 70, 30);

		// 3.设置写入数据的字体颜色和字体
		g.setColor(Color.WHITE);
		g.setFont(new Font(null, Font.BOLD, 20));

		// 4.向图片上写数据
		String num = produceNum();
		request.getSession().setAttribute("checkcode", num);
		g.drawString(num, 0, 20);

		// 释放资源
		g.dispose();

		// 5.把写好数据的图片传送给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());

	}

	/* 产生随机数验证码 */
	private String produceNum() {
		Random random = new Random();
		String num = (random.nextInt(89999) + 10000) + "";
		return num;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
