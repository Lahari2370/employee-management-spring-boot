package com.example.employee_management.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_management.DTOO.EmployeeResponseDTO;
import com.example.employee_management.dto.EmployeeDTO;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.EmployeeRepository;



@Service

public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  
  
	public EmployeeService(EmployeeRepository employeeRepository) {
	this.employeeRepository=employeeRepository;
	
	}
	
	public List<EmployeeResponseDTO> getAllEmployees() {
	    return employeeRepository.findAll()
	            .stream()
	            .map(employee -> new EmployeeResponseDTO(
	                    employee.getId(),
	                    employee.getName(),
	                    employee.getEmail(),
	                    employee.getDepartment(),
	                    employee.getSalary()
	            ))
	            .toList();
	}
	public EmployeeResponseDTO createEmployee(EmployeeDTO dto) {

	    Employee employee = new Employee();

	    employee.setName(dto.getName());
	    employee.setEmail(dto.getEmail());
	    employee.setDepartment(dto.getDepartment());
	    employee.setSalary(dto.getSalary());

	    Employee savedEmployee = employeeRepository.save(employee);

	    return new EmployeeResponseDTO(
	            savedEmployee.getId(),
	            savedEmployee.getName(),
	            savedEmployee.getEmail(),
	            savedEmployee.getDepartment(),
	            savedEmployee.getSalary()
	    );
	}

	public EmployeeResponseDTO getEmployeeById(Long id) {
	    Employee employee = employeeRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));

	    return new EmployeeResponseDTO(
	            employee.getId(),
	            employee.getName(),
	            employee.getEmail(),
	            employee.getDepartment(),
	            employee.getSalary()
	    );
	}
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeDTO dto) {

	    Employee existingEmployee = employeeRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));

	    existingEmployee.setName(dto.getName());
	    existingEmployee.setEmail(dto.getEmail());
	    existingEmployee.setDepartment(dto.getDepartment());
	    existingEmployee.setSalary(dto.getSalary());

	    Employee updatedEmployee = employeeRepository.save(existingEmployee);

	    return new EmployeeResponseDTO(
	            updatedEmployee.getId(),
	            updatedEmployee.getName(),
	            updatedEmployee.getEmail(),
	            updatedEmployee.getDepartment(),
	            updatedEmployee.getSalary()
	    );
	}
	public void deleteEmployee(Long id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new RuntimeException("Employee not found");
	    }

	    employeeRepository.deleteById(id);
	}
}

