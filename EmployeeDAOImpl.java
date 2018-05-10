package com.htc.spring4.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.htc.spring4.dto.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public boolean insertEmp(int empno, String empname, String job, Date date, double salary, int deptno) {
		
		String sql = "insert into TRNG_EMPLOYEE values(?,?,?,?,?,?)";
		int insrtCount = jdbcTemplate.update(sql, new Integer(empno), empname, job, new Double(salary), date, deptno);
		if(insrtCount>0) 
			return true;
		return false;
	}

	@Override
	public Employee getEmployee(int empno) {
		
		String sql = "select EMPNO, EMPNAME, JOB, SALARY,DOJ,DEPT from TRNG_EMPLOYEE where EMPNO=?";
		Employee emp = jdbcTemplate.queryForObject(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setJoindate(rs.getDate(5));
				emp.setDeptno(rs.getInt(6));
				return emp;
			}
		}, new Integer(empno) );
		
		return emp;
	}

	//another way
	public Employee getEmployee1(int empno) {
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		String sql = "select EMPNO, EMPNAME, JOB, SALARY,DOJ,DEPT from TRNG_EMPLOYEE where EMPNO= :eno";
		Employee emp = namedJdbcTemplate.queryForObject(sql, Collections.singletonMap("eno", new Integer(empno)), new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setJoindate(rs.getDate(5));
				emp.setDeptno(rs.getInt(6));
				return emp;
			}
		});
		return emp;
	}

	
	@Override
	public List<Employee> getEmployees(int deptno) {
		
		String sql = "select EMPNO, EMPNAME, JOB, SALARY,DOJ,DEPT from TRNG_EMPLOYEE where DEPTNO=?";
		List<Employee> empList = jdbcTemplate.query(sql, new Object[] {new Integer(deptno)},new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setJoindate(rs.getDate(5));
				emp.setDeptno(rs.getInt(6));
				return emp;
			}
			
		});
		return empList;
	}

	@Override
	public List<Employee> getEmployees(String designation) {
		String sql = "select EMPNO, EMPNAME, JOB, SALARY,DOJ,DEPT from TRNG_EMPLOYEE where job=?";
		List<Employee> empList = jdbcTemplate.query(sql, new Object[] {designation},new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setJoindate(rs.getDate(5));
				emp.setDeptno(rs.getInt(6));
				return emp;
			}
			
		});
		return empList;
	}

	
	@Override
	public Integer getEmployeeCount(String designation) {

		String sql = "select count(*) from TRNG_EMPLOYEE where job=?";
		Integer designationCount = jdbcTemplate.queryForObject(sql, new Object[] {designation}, Integer.class);
		
		return designationCount;
	}

	//another way
	public Integer getEmployeeCount1(String designation) {

		String sql = "select count(*) from TRNG_EMPLOYEE where job = :job";
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		Integer designationCount = namedJdbcTemplate.queryForObject(sql, Collections.singletonMap("job", designation), Integer.class);
		return designationCount;
	}

	//Using IN operator
	public List<Employee> getEmployeesWithSkills(List<String> skillIds) {
		
		String sql = "select EMPNO, EMPNAME, JOB, SALARY,DOJ,DEPT from TRNG_EMPLOYEE where skillId in (:skillIds)";
		SqlParameterSource parameterSource = new MapSqlParameterSource("skillIds", skillIds);
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Employee> empList = namedJdbcTemplate.query(sql, parameterSource, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowno) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEmpname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSalary(rs.getDouble(4));
				emp.setJoindate(rs.getDate(5));
				emp.setDeptno(rs.getInt(6));
				return emp;
			}
			
		});
		return empList;
	}
	
	@Override
	public String getEmployeeName(int empno) {
		
		String sql = "select empname from TRNG_EMPLOYEE where EMPNO=?";
		String empName= jdbcTemplate.queryForObject(sql, new Object[] {new Integer(empno)}, String.class);
		
		return empName;
	}

	@Override
	public BigDecimal callProcedure(int empno) {
		BigDecimal percentage = new BigDecimal(0);
		try {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
										.withProcedureName("UPDATESALARY")
										.withoutProcedureColumnMetaDataAccess()
										.useInParameterNames("eno")
										.declareParameters(
											new SqlParameter("eno", Types.NUMERIC),
											new SqlOutParameter("incrementPercentage", Types.NUMERIC));
		
		SqlParameterSource inParameterSource = new MapSqlParameterSource()
										.addValue("eno", empno);
		
		Map<String,Object> outParam = jdbcCall.execute(inParameterSource);
		 percentage = (BigDecimal) outParam.get("incrementPercentage");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return percentage;
	}

	
}
