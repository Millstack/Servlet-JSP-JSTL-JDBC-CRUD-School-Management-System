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
import com.jsp.dto.Student;

@WebServlet("/updatestudent")
public class UpdateStudent extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");
		String qualification = req.getParameter("qualification");
		String email = req.getParameter("email");
		int id = Integer.parseInt(req.getParameter("id"));
		
		Student student = new Student(id, name, dob, address, qualification, email);
		
		StudentDao studentDao = new StudentDao(DbConnect.getConn());
		
		HttpSession session = req.getSession();
		
		boolean b = studentDao.updateStudent(student);
		
		if(b) {
			session.setAttribute("succMsg", "Student updated successfuly");
			resp.sendRedirect("index.jsp");
			System.out.println("Student updated successfuly");
		}
		else {
			session.setAttribute("errorMsg", "Student failed to get update");
			resp.sendRedirect("index.jsp");
			System.out.println("Student faield from getting update");
		}	
	}
}