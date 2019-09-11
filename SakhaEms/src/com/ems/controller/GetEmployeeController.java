package com.ems.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.model.Employee;
import com.ems.service.EmployeeServiceImpl;

/**
 * Servlet implementation class GetEmployeeController
 */
@WebServlet({ "/GetEmployeeController", "/getDetails" })
public class GetEmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeServiceImpl es;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empId=request.getParameter("empId");
		try
		{
			 es=new EmployeeServiceImpl();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Employee emp=null;
		try {
				emp=es.getEmployee(empId);
				
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
