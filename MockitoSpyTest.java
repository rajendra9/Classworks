package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import code.Employee;

public class MockitoSpyTest {

	private Employee spyEmp;
    private Employee emp;

	@Before
	public void buildSpy() {
		emp = new Employee(101,"Ajay", "Varma", 50000.0,"Tester");
		spyEmp = Mockito.spy(emp);
	}
	
	@Test
	public void verifySpyEffectOnRealInstance() {
		spyEmp.setSalary(75000.0);
		Assert.assertFalse(emp.getSalary()== spyEmp.getSalary());
	}
	
	@Test
	public void verifyEmployeeDetails() {
		System.out.println("Full name:" + spyEmp.getFullName());
		System.out.println("Salary:" + spyEmp.getSalary());
		
		InOrder inOrder = Mockito.inOrder(spyEmp);
		
		System.out.println("Verify emp.getFullName() calls getFirstName() and then getLastName()");
		inOrder.verify(spyEmp).getFirstName();
		inOrder.verify(spyEmp).getLastName();
		
		System.out.println("Verify emp.getAge() is called");
		Mockito.verify(spyEmp).getSalary();
		
		System.out.println("Verify emp.getFullName() called twice");
		Mockito.verify(spyEmp, Mockito.times(1)).getFullName();
	}
	
}
