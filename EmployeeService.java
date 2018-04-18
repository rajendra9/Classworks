package code;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public boolean addEmployee(Employee emp) {
		return employees.add(emp);
	}
	
	public int empCount() {
		return employees.size();
	}
	
	public double getSalary(int empno) {
		double salary = 0.0;
		for(Employee emp : employees) {
			if(emp.getEmpno() == empno) {
				salary = emp.getSalary();
				break;
			}
		}
		return salary;
	}
	
	public Employee getEmployee(int empno) {
		Employee employee = null;
		for(Employee emp : employees) {
			if(emp.getEmpno() == empno) {
				employee = emp;
				break;
			}
		}
		return employee;
	}
	
	public boolean searchEmployee(int empno) {
		boolean empFound = false;
		for(Employee emp : employees) {
			if(emp.getEmpno() == empno) {
				empFound = true;
				break;
			}
		}
		return empFound;
	}
	
	public String[] getEmployees(String designation) {
		
		List<String> empNames = new ArrayList<String>();
		for(Employee emp : employees) {
			if(emp.getDesignation().equals(designation)) {
				empNames.add(emp.getFullName());	
			}
		}
		String[] namesArray = new String[empNames.size()];
		return empNames.toArray(namesArray);
	}
	
	public void updateSalary(int empno, double newSalary) {
		for(Employee emp : employees) {
			if(emp.getEmpno() == empno) {
				emp.setSalary(newSalary);
				break;
			}
		}	
	}
	
	
	public void deleteEmployee(Employee emp) {
		employees.remove(emp);
	}	
}
