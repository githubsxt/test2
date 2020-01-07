package com.yq.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yq.dao.StudentDao;
import com.yq.model.Student;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	StudentDao studentDao = new StudentDao(); 
	ObjectMapper mapper = new ObjectMapper();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("获取student对象");
		Student student = studentDao.findStudentById(1);
		List<Student> stus = studentDao.findAll();
		response.setContentType("application/json;charset=UTF-8");
//		String data = mapper.writeValueAsString(student);
		String data = mapper.writeValueAsString(stus);
		List<Student> list = mapper.readValue(data, new TypeReference<List<Student>>() {});
		System.out.println(list);
		response.getWriter().append(data);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=============");
		Map<String,String[]> map = req.getParameterMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key + " --> " + Arrays.toString(map.get(key)));
		}
		String obj = req.getParameter("obj");
		System.out.println(obj);
		Student s = mapper.readValue(obj, Student.class);
		System.out.println(s);
		
	}
}
