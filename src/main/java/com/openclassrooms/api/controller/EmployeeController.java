package com.openclassrooms.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api.model.Employee;
import com.openclassrooms.api.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * Create - Add a new employee
	 * @param employee An object employee
	 * @return The employee object saved
	 */
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	
	/**
	 * Read - Get one employee 
	 * @param id The id of the employee
	 * @return An Employee object full filled
	 */
	@GetMapping("employees/{id}")
	public Employee getEmployee(@PathVariable("id") final Long id) {
		Optional<Employee> employeeId = employeeService.getEmployee(id);
		
		if (employeeId.isEmpty()) {
			return employeeId.get();
		}
		else {
			return null;
		}
	}
	
	 /**
	 * Read - Get all employees
	 * @return - An Iterable object of Employee full filled
	 */
	@GetMapping("/employees")
	public Iterable<Employee> getEmployees(){
		return employeeService.getEmployees();
	}
	
	/**
	 * Update - Update an existing employee
	 * @param id - The id of the employee to update
	 * @param employee - The employee object updated
	 * @return
	 */
	@PutMapping("/employee/{id}")
	
	public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
		Optional<Employee> employeeToUpdate = employeeService.getEmployee(id);
		if(employeeToUpdate.isPresent()) {
			Employee currentEmployee = employeeToUpdate.get();
			
			String firstName = employee.getFirst_name();
			if(firstName != null) {
				currentEmployee.setFirst_name(firstName);
			}
			
			String lastName = employee.getLast_name();
			if(lastName!=null) {
				currentEmployee.setLast_name(lastName);
			}
			String mail = employee.getMail();
			if(mail != null) {
				currentEmployee.setMail(mail);
			}
			String password = employee.getPassword();
			if(password != null) {
				currentEmployee.setPassword(password);
			}
			employeeService.saveEmployee(currentEmployee);
			return currentEmployee;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Delete - Delete an employee
	 * @param id - The id of the employee to delete
	 */
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") final Long id) {
		employeeService.deleteEmployee(id);
	}

}
