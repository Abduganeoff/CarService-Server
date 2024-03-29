package com.carservice.thesis.service;

import com.carservice.thesis.entity.Employee;
import com.carservice.thesis.exception.NotFoundException;
import com.carservice.thesis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployee(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllUnassignedEmployees() {
        return employeeRepository.findAllUnassignedEmployees();
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        employee.setFirstname(employeeDetails.getFirstname());
        employee.setLastname(employeeDetails.getLastname());
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setEmail(employeeDetails.getEmail());
        employee.setStation(employeeDetails.getStation());
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        if (employee.getStation() != null) {
            employee.getStation().getEmployees().remove(employee);
            employee.setStation(null);
        }
        employeeRepository.delete(employee);
    }
}
