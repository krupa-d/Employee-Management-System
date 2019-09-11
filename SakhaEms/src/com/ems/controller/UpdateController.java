package com.ems.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.model.Employee;
import com.ems.service.EmployeeServiceImpl;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet({ "/UpdateController", "/update" })
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empId=request.getParameter("eid");
		Float basicSalary=Float.parseFloat(request.getParameter("sal"));
		
		Employee emp=new Employee();
		emp.setEmpId(empId);
		emp.setBasicSalary(basicSalary);
	
		EmployeeServiceImpl es=new EmployeeServiceImpl();
		try
		{
			if(es.update(emp))
			{
				response.sendRedirect("Success.jsp");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
