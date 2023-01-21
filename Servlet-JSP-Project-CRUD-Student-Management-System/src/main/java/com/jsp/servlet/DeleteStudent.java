package com.jsp.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.connection.DbConnect;
import com.jsp.dao.StudentDao;


@WebServlet("/deletestudent")
public class DeleteStudent extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		StudentDao studentDao = new StudentDao(DbConnect.getConn());
		
		HttpSession session = req.getSession();
		
		boolean b = studentDao.deleteStudent(id);
		
		if(b) {
			session.setAttribute("succMsg", "Student deleted successfuly");
			resp.sendRedirect("index.jsp");
			System.out.println("Student deleted successfuly");
		}
		else {
			session.setAttribute("errorMsg", "Student failed to get delete");
			resp.sendRedirect("index.jsp");
			System.out.println("Student faield from getting deleted");
		}
	}
}