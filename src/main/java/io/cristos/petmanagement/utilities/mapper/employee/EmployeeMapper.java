package io.cristos.petmanagement.utilities.mapper.employee;

import io.cristos.petmanagement.dtos.request.employee.EmployeeRequestDto;
import io.cristos.petmanagement.dtos.response.employee.EmployeeResponseDto;
import io.cristos.petmanagement.models.employee.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeMapper {

    Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto);

    Employee employeeRequestDtoToEmployee(Long employeeId, EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee);

    List<EmployeeResponseDto> employeeListToEmployeeResponseDtoList(Collection<Employee> employeeCollection);

    Employee createEmployeeFromEmployeeRequestDto(EmployeeRequestDto employeeRequestDto);
}
