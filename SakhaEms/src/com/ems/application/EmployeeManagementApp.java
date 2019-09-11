package com.ems.application;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import com.ems.model.Employee;
import com.ems.service.EmployeeService;
import com.ems.service.EmployeeServiceImpl;

public class EmployeeManagementApp {
	
	EmployeeService es;
	BufferedReader in;
	public EmployeeManagementApp()
	{
		es=new EmployeeServiceImpl();
		in=new BufferedReader(new InputStreamReader(System.in));
	}
	public void addEmployee()throws Exception
	{
		String empName;
		LocalDate dob;
		float sal;
		System.out.println("Enter employee details(name,dob(dd-mm-yyyy),salary)");
		empName=in.readLine();
		String strDate=in.readLine();
		StringTokenizer stk=new StringTokenizer(strDate,"-");
		int date=Integer.parseInt(stk.nextToken());
		int month=Integer.parseInt(stk.nextToken());
		int year=Integer.parseInt(stk.nextToken());
		dob=LocalDate.of(year, month, date);
		sal=Float.parseFloat(in.readLine());
		
		Employee emp=new Employee();
		emp.setEmpName(empName);
		emp.setBasicSalary(sal);
		emp.setDob(dob);
		
		es.save(emp);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeManagementApp app=new EmployeeManagementApp();
		try
		{
			app.addEmployee();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
