package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import code.Employee;
import code.EmployeeService;

public class EmployeeTest {

	EmployeeService empService;
	
	@Before  
	public void setUp() {
		empService = new EmployeeService();
	}
	
	@Test
	public void testAddEmployee() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertEquals(2, empService.empCount());
	}
	
	@Test
	public void testGetSalary() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertEquals(45000.0, empService.getSalary(102),.001);
	}
	
	@Test
	public void testGetEmployee1() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertNull(empService.getEmployee(103));
	}
	
	@Test
	public void testGetEmployee2() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertNotNull(empService.getEmployee(101));
	}
	
	@Test
	public void testSearchEmployee1() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertTrue(empService.searchEmployee(102));
	}

	@Test
	public void testSearchEmployee2() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		Assert.assertFalse(empService.searchEmployee(103));
	}
	
	@Test
	public void testGetEmployeesNameWithGivenDesignation() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishnan",45000.0,"Tester"));
		empService.addEmployee(new Employee(103,"Ajay","Varma", 30000.0,"Developer"));
		empService.addEmployee(new Employee(104,"Anil","Kumar",55000.0,"Tester"));
		Assert.assertArrayEquals(new String[]{"Ramesh Krishnan", "Anil Kumar"}, empService.getEmployees("Tester"));
	}
	
	@Test
	public void testUpdateEmpSalary() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		empService.updateSalary(101, 55000.0);
		Assert.assertEquals(55000.0, empService.getSalary(101),0.001);   //Testing method side effects.
	}
	
	@Test
	public void testDeleteEmployee() {
		empService.addEmployee(new Employee(101,"Rahul","Dravid",50000.0,"Developer"));
		empService.addEmployee(new Employee(102,"Ramesh", "Krishan",45000.0,"Tester"));
		empService.addEmployee(new Employee(103,"Ajay","Varma", 30000.0,"Developer"));
		
		empService.deleteEmployee(new Employee(103,"Ajay","Varma", 30000.0,"Developer"));
		Assert.assertNull(empService.getEmployee(103));    //Testing side effects.
	}
	
	
 }
 
