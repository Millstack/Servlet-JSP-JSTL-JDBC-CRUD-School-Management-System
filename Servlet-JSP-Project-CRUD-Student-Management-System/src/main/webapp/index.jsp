<%@page import="com.jsp.dto.Student"%>
<%@page import="com.jsp.dao.StudentDao"%>
<%@page import="com.jsp.connection.DbConnect"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>School Management System</title>
<%@ include file="css.jsp"%>
</head>
<body class="bg-light">
	<%@ include file="navbar.jsp"%>

	<div class="container p-5">
		<div class="card">

			<div class="card-body">
				<p class="text-center fs-2">Student Management System</p>
				<c:if test="${not empty succMsg}">
					<p class="text-center text-success">${succMsg}</p>
					<c:remove var="succMsg" />
				</c:if>

				<c:if test="${not empty errorMsg}">
					<p class="text-center text-success">${errorMsg}</p>
					<c:remove var="errorMsg" />
				</c:if>
				<table class="table text-center">
					<thead>
						<tr class="fs-5">
							<th scope="col">Student Name</th>
							<th scope="col">DOB</th>
							<th scope="col">Address</th>
							<th scope="col">Qualification</th>
							<th scope="col">Email</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>

						<%
							StudentDao studentDao = new StudentDao(DbConnect.getConn());
							List<Student> stu = studentDao.getAllStudent();
							for (Student s : stu) {
						%>
						<tr>
							<th scope="row"><%=s.getName()%></th>
							<td><%=s.getDob()%></td>
							<td><%=s.getAddress()%></td>
							<td><%=s.getQualification()%></td>
							<td><%=s.getEmail()%></td>
							<td>
								<a href="update-student.jsp?id=<%=s.getId()%>" class="btn btn-sm btn-dark">Update</a>
								<a href="deletestudent?id=<%=s.getId()%>" class="btn btn-sm btn-danger ms-1">Delete</a>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>