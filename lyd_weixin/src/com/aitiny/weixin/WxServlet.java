package com.aitiny.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
@WebServlet("/weixinmain.do")
public class WxServlet extends HttpServlet {

	@Override
	public  void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out=new PrintWriter(resp.getOutputStream());
		
		connect(req, out);
	}
	/*
	 * ����
	 */
	private void connect(HttpServletRequest req,PrintWriter out){
		String signature=req.getParameter("signature");
		String timestamp=req.getParameter("timestamp");
		String nonce=req.getParameter("nonce");
		String echostr=req.getParameter("echostr");
		System.out.println(req.getRequestURL().toString());
		System.out.println(signature+","+timestamp+","+nonce+","+echostr);
		List<String> list=new ArrayList<String>();
		list.add("koala");
		list.add(timestamp);
		list.add(nonce);

		Collections.sort(list);

		StringBuffer sb=new  StringBuffer();
		for (String str : list) {
			sb.append(str);
		}
		//apache,commons,codec
		String sha1Str=DigestUtils.sha1Hex(sb.toString());

		boolean flag=sha1Str.equals(signature);
		if(flag){
			System.out.println("连接成功");
			out.print(echostr);
			out.flush();
		}else{
			System.out.println("连接失败");
		}
		
		
		
	}
	
	
	
}
