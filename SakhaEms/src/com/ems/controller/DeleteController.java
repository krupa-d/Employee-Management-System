package com.ems.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.service.EmployeeServiceImpl;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet({ "/DeleteController", "/delete" })
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String empId=request.getParameter("id");
		EmployeeServiceImpl es=new EmployeeServiceImpl();
		try {
			if(es.delete(empId))
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
