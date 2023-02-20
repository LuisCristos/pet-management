package io.cristos.petmanagement.services.employee;

import io.cristos.petmanagement.dtos.request.employee.EmployeeRequestDto;
import io.cristos.petmanagement.dtos.response.employee.EmployeeResponseDto;
import io.cristos.petmanagement.models.employee.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequestDto employeeRequestDto);

    List<EmployeeResponseDto> getALlEmployees();

    EmployeeResponseDto findEmployeeById(Long employeeId);

    void deleteEmployee(Long employeeId);

    Employee updateEmployeeById(Long employeeId, EmployeeRequestDto employeeRequestDto);

    Employee returnEmployeeIfExists(Long employeeId);
}
