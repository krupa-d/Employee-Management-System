package com.ems.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.ems.dao.EmployeeDao;
import com.ems.dao.EmployeeDaoImpl;
import com.ems.model.Employee;
import com.ems.util.*;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao dao;
	public EmployeeServiceImpl()
	{
		dao=new EmployeeDaoImpl();
	}
	@Override
	public boolean validateEmployee(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		String empName=emp.getEmpName();
		LocalDate dob=emp.getDob();
		float basicSalary=emp.getBasicSalary();
		
		if(empName.length()<4 || empName.length()>15)
		{
			throw new InvalidNameException("Name must be in 4-15 Characters");
		}
		if(dob.isAfter(LocalDate.of(1995,12,31))||dob.isBefore(LocalDate.of(1900, 1, 1)))
		{
			throw new InvalidDobException("Please enter dob in range 01-01-1900-31-12-1995");
		}
		if(basicSalary>80000 || basicSalary<20000)
		{
			throw new InvalidSalaryException("Please enter the salary between 80000 and 20000");
		}
		return true;
	}
	
	public boolean validateEmployee1(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		float basicSalary=emp.getBasicSalary();
		if(basicSalary>80000 || basicSalary<20000)
		{
			throw new InvalidSalaryException("Please enter the salary between 80000 and 20000");
		}
		return true;
	}

	@Override
	public String generateId(String empname) throws Exception {
		// TODO Auto-generated method stub
		String nameChar=empname.substring(0,2).toUpperCase();
		Random rand=new Random();
		int dgt=(int)(rand.nextDouble()*10000);
		return nameChar+dgt;
	}

	@Override
	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ems","lenevo-440s","welcome");
		return con;
	}

	@Override
	public boolean save(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		if(validateEmployee(emp))
		{
			emp.setEmpId(generateId(emp.getEmpName()));
			return dao.save(emp);
		}
		return false;
	}

	@Override
	public boolean delete(String empId) throws Exception {
		// TODO Auto-generated method stub
		return dao.delete(empId);
	}

	@Override
	public boolean update(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		if(validateEmployee1(emp))
		{
			return dao.update(emp);
		}
		return false;
	}

	@Override
	public Employee getEmployee(String empId) throws Exception {
		// TODO Auto-generated method stub
		return dao.getEmployee(empId);
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllEmployees();
	}

}
