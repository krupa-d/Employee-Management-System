package com.ems.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ems.model.Employee;
import com.ems.service.EmployeeServiceImpl;

/**
 * Servlet implementation class AddController
 */
@WebServlet({ "/AddController", "/add" })
public class AddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String empName=request.getParameter("name");
		String strdob=request.getParameter("dob");
		LocalDate dob;
		StringTokenizer stk=new StringTokenizer(strdob,"-");
		int date=Integer.parseInt(stk.nextToken());
		int month=Integer.parseInt(stk.nextToken());
		int year=Integer.parseInt(stk.nextToken());
		dob=LocalDate.of(year, month, date);
		float basicsalary=Float.parseFloat(request.getParameter("salary"));
		
		Employee emp=new Employee();
		emp.setEmpName(empName);
		emp.setBasicSalary(basicsalary);
		emp.setDob(dob);
		
		EmployeeServiceImpl es=new EmployeeServiceImpl();
		try {
			if(es.save(emp))
			{
				
				response.sendRedirect("Success.jsp");
			}
			else
			{
				response.sendRedirect("Failure.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
