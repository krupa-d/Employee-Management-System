package com.ems.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.ems.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	Connection con;
	
	@Override
	public boolean save(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?)");
		ps.setString(1, emp.getEmpId());
		ps.setString(2, emp.getEmpName());
		LocalDate dobRaw=emp.getDob();
		ps.setDate(3,new java.sql.Date(dobRaw.getYear()-1900,dobRaw.getMonthValue()-1,dobRaw.getDayOfMonth()));
		ps.setFloat(4, emp.getBasicSalary());
		
		int r=ps.executeUpdate();
		return r>0?true:false;
	}

	@Override
	public boolean delete(String empId) throws Exception{
		// TODO Auto-generated method stub
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from employee where empid=?");
		ps.setString(1,empId);
		int r=ps.executeUpdate();
		return r>0?true:false;
	}

	@Override
	public boolean update(Employee emp) throws Exception {
		// TODO Auto-generated method stub
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("Update employee set basic_salary=? where empid=?");
		ps.setFloat(1,emp.getBasicSalary());
		ps.setString(2,emp.getEmpId());
		int r=ps.executeUpdate();
		return r>0?true:false;
	}

	@Override
	public Employee getEmployee(String empId)throws Exception {
		// TODO Auto-generated method stub
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from employee where empid=?");
		ps.setString(1,empId);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			java.sql.Date rowDate=rs.getDate(3); 
			Employee emp=new Employee(rs.getString(1),rs.getString(2),LocalDate.of(rowDate.getYear(), rowDate.getMonth(),rowDate.getDate()),rs.getFloat(4));
			return emp;
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() throws Exception {
		// TODO Auto-generated method stub
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from employee");
		List<Employee> empList=new ArrayList<>();
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			java.sql.Date rowDate=rs.getDate(3); 
			Employee emp=new Employee(rs.getString(1),rs.getString(2),LocalDate.of(rowDate.getYear(), rowDate.getMonth(),rowDate.getDate()),rs.getFloat(4));
			empList.add(emp);
		}
		return empList;
	}
	
	@Override
	public Connection getConnection() throws Exception {
		// TODO Auto-generated method stub
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ems","lenevo-440s","welcome");
		return con;
	}

}
