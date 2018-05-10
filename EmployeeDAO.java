package com.htc.spring4.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.htc.spring4.dto.Employee;

public interface EmployeeDAO {

	public boolean insertEmp(int empno, String empname, String job, Date date, double salary, int deptno) ;

	public Employee getEmployee(int empno);
	
	public List<Employee> getEmployees(int deptno);

	public List<Employee> getEmployees(String designation);
	
	public Integer getEmployeeCount(String designation);

	public String getEmployeeName(int empno);
	
	public BigDecimal callProcedure(int empno);
}
