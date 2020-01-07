package com.yq.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yq.dao.ClerkDao;
import com.yq.model.Clerk;

@WebServlet("/clerk")
public class ClerkServlet extends HttpServlet {
	
	ClerkDao clerkDao = new ClerkDao(); 
	ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("获取clerk对象");
		List<Clerk> stus = clerkDao.findAll();
		response.setContentType("application/json;charset=UTF-8");
		String data = mapper.writeValueAsString(stus);
		response.getWriter().append(data);
	}

}
