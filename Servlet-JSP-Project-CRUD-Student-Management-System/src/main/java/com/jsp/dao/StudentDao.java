package com.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.dto.Student;
import com.mysql.cj.protocol.Resultset;

public class StudentDao {

	private Connection conn;
	
//	to get connection
	public StudentDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
//	save student
	public boolean saveStudent(Student student) {
		
		boolean b = false;
		String sql = "INSERT INTO Student(name, dob, address, qualification, email) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getDob());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getQualification());
			preparedStatement.setString(5, student.getEmail());
			
			int i = preparedStatement.executeUpdate();
			if(i==1) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	update student
	public boolean updateStudent(Student student) {
		
		boolean b = false;
		String sql = "UPDATE Student SET name=?, dob=?, address=?, qualification=?, email=? WHERE ID=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getDob());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getQualification());
			preparedStatement.setString(5, student.getEmail());
			preparedStatement.setInt(6, student.getId());
			
			int i = preparedStatement.executeUpdate();
			if(i==1) {
				b=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	get stuent by id
	public Student getStudentById(int id) {
		Student student = null;
		String sql = "SELECT * FROM STUDENT WHERE ID = ?";
		List<Student> s = new ArrayList();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				student = new Student();
				student.setId(resultset.getInt(1));
				student.setName(resultset.getString(2));
				student.setDob(resultset.getString(3));
				student.setAddress(resultset.getString(4));
				student.setQualification(resultset.getString(5));
				student.setEmail(resultset.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	
//	get all student
	public List<Student> getAllStudent(){
		
		Student student = null;
		String sql = "SELECT * FROM student";
		List<Student> s = new ArrayList();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultset = preparedStatement.executeQuery();
			while(resultset.next()) {
				student = new Student();
				student.setId(resultset.getInt(1));
				student.setName(resultset.getString(2));
				student.setDob(resultset.getString(3));
				student.setAddress(resultset.getString(4));
				student.setQualification(resultset.getString(5));
				student.setEmail(resultset.getString(6));
				s.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	
//	delete student
	public boolean deleteStudent(int id) {
		
		boolean b = false;
		String sql = "DELETE FROM STUDENT WHERE ID = ?";
		try {
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			int i = preparedStatement.executeUpdate();
			if(i==1) {
				b=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
}