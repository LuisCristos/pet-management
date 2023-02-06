package io.cristos.petmanagement.utilities.mapper.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
import io.cristos.petmanagement.models.employee.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> employeeListToEmployeeDtoList(Collection<Employee> employeeCollection);
}
