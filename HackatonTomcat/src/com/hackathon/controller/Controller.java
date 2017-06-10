package com.hackathon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = req.getReader();
		while((line=reader.readLine())!=null){
			jb.append(line);
		}
		System.out.println(jb.toString());
		PrintWriter pw = resp.getWriter();
		pw.write(jb.toString());
		
	}
	
}
