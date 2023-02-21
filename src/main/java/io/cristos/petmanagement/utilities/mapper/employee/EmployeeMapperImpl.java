package io.cristos.petmanagement.utilities.mapper.employee;

import io.cristos.petmanagement.dtos.request.employee.EmployeeRequestDto;
import io.cristos.petmanagement.dtos.response.employee.EmployeeResponseDto;
import io.cristos.petmanagement.models.employee.Employee;
import io.cristos.petmanagement.utilities.genderconverter.GenderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    private final GenderConverter genderConverter;

    @Autowired
    public EmployeeMapperImpl(GenderConverter genderConverter) {
        this.genderConverter = genderConverter;
    }

    @Override
    public Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto) {

        Employee employee = createEmployeeFromEmployeeRequestDto(employeeRequestDto);

        return employee;
    }

    @Override
    public Employee employeeRequestDtoToEmployee(Long employeeId, EmployeeRequestDto employeeRequestDto) {

        Employee employee = createEmployeeFromEmployeeRequestDto(employeeRequestDto);

        employee.setId(employeeId);

        return employee;
    }

    @Override
    public EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee) {

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        employeeResponseDto.setId(employee.getId());
        employeeResponseDto.setFirstName(employee.getFirstName());
        employeeResponseDto.setLastName(employee.getLastName());
        employeeResponseDto.setAge(employee.getAge());
        employeeResponseDto.setBornAt(employee.getBornAt());
        employeeResponseDto.setCreatedAt(employee.getCreatedAt());
        employeeResponseDto.setGender(genderConverter.convertToDatabaseColumn(employee.getGender()));

        return employeeResponseDto;
    }

    @Override
    public List<EmployeeResponseDto> employeeListToEmployeeResponseDtoList(Collection<Employee> employeeCollection) {

        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();

        for (Employee employee : employeeCollection) {

            EmployeeResponseDto employeeResponseDto = employeeToEmployeeResponseDto(employee);

            employeeResponseDtoList.add(employeeResponseDto);
        }

        return employeeResponseDtoList;
    }

    @Override
    public Employee createEmployeeFromEmployeeRequestDto(EmployeeRequestDto employeeRequestDto) {

        Employee employee = new Employee();

        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setBornAt(employeeRequestDto.getBornAt());
        employee.setGender(genderConverter.convertToEntityAttribute(employeeRequestDto.getGender()));

        return employee;
    }
}
