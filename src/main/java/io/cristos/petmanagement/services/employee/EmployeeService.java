package io.cristos.petmanagement.services.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
import io.cristos.petmanagement.models.employee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getALlEmployees();

    EmployeeDto findEmployeeById(Long employeeId);

    void deleteEmployee(Long employeeId);

    Employee updateEmployeeById(Long employeeId, EmployeeDto employeeDto);
}
