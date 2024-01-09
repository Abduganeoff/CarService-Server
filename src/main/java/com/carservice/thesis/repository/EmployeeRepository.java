package com.carservice.thesis.repository;

import com.carservice.thesis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.station IS NULL")
    List<Employee> findAllUnassignedEmployees();
}
