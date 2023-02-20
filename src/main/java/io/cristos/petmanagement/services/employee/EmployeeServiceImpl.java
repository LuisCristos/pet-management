package io.cristos.petmanagement.services.employee;

import io.cristos.petmanagement.dtos.request.employee.EmployeeRequestDto;
import io.cristos.petmanagement.dtos.response.employee.EmployeeResponseDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.employee.Employee;
import io.cristos.petmanagement.repositories.employee.EmployeeRepository;
import io.cristos.petmanagement.utilities.mapper.employee.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee saveEmployee(EmployeeRequestDto employeeRequestDto) {

        return employeeRepository.save(employeeMapper.employeeRequestDtoToEmployee(employeeRequestDto));
    }

    @Override
    public List<EmployeeResponseDto> getALlEmployees() {

        Collection<Employee> employeeCollection = employeeRepository.findAll();

        if (employeeCollection.isEmpty()) {
            logger.info("getALlEmployees(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return employeeMapper.employeeListToEmployeeResponseDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeResponseDto findEmployeeById(Long employeeId) {

        Employee employee = returnEmployeeIfExists(employeeId);

        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        returnEmployeeIfExists(employeeId);

        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee updateEmployeeById(Long employeeId, EmployeeRequestDto employeeRequestDto) {

        returnEmployeeIfExists(employeeId);

        return employeeRepository.save(employeeMapper.employeeRequestDtoToEmployee(employeeId, employeeRequestDto));
    }

    @Override
    public Employee returnEmployeeIfExists(Long employeeId) {

        Optional<Employee> optionalEmployee = Optional.ofNullable(employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}!",
                            "An exception occurred!", "Employee with id: " + employeeId + " cannot be found.",
                            new NotFoundException("Employee with id: " + employeeId + " cannot be found."));

                    throw new NotFoundException("Employee with id: " + employeeId + " cannot be found.");
                }));

        return optionalEmployee.get();
    }
}
