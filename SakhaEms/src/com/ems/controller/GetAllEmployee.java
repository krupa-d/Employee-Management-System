package com.ems.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.model.Employee;
import com.ems.service.EmployeeServiceImpl;

/**
 * Servlet implementation class GetAllEmployee
 */
@WebServlet({ "/GetAllEmployee", "/details" })
public class GetAllEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeServiceImpl es;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		try
		{
			 es=new EmployeeServiceImpl();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		List<Employee> emp=null;
		try {
				emp=es.getAllEmployees();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("Failure.jsp");
		}
		HttpSession ssn=request.getSession();
		ssn.setAttribute("emp",emp);
		response.sendRedirect("showDetails.jsp");
	}

}
