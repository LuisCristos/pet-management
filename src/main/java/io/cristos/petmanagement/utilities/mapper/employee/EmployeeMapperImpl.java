package io.cristos.petmanagement.utilities.mapper.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
import io.cristos.petmanagement.models.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public EmployeeDto employeeToEmployeeDto(Employee employee) {

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDateOfBirth(employee.getBornAt());
        employeeDto.setDateOfCreation(employee.getCreatedAt());
        employeeDto.setAge(employee.getAge());

        return employeeDto;
    }

    @Override
    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setBornAt(employeeDto.getDateOfBirth());
        employee.setCreatedAt(employeeDto.getDateOfCreation());
        employee.setAge(employeeDto.getAge());

        return employee;
    }

    @Override
    public List<EmployeeDto> employeeListToEmployeeDtoList(Collection<Employee> employeeCollection) {

        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        for (Employee employee : employeeCollection) {

            EmployeeDto employeeDto = employeeToEmployeeDto(employee);

            employeeDtoList.add(employeeDto);
        }

        return employeeDtoList;
    }
}
