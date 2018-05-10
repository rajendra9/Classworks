package com.htc.spring4.dto;

import java.util.Date;

public class Employee {

	private int empno;
	private String empname;
	private String job;
	private double salary;
	private Date joindate;
	private int deptno;
	
	public Employee() {}

	public Employee(int empno, String empname, String job, double salary, Date joindate, int deptno) {
		super();
		this.empno = empno;
		this.empname = empname;
		this.job = job;
		this.salary = salary;
		this.joindate = joindate;
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", empname=" + empname + ", job=" + job + ", salary=" + salary + ", deptno="
				+ deptno + "]";
	}
	
}
