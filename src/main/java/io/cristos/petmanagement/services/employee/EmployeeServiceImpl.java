package io.cristos.petmanagement.services.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
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
    public Employee saveEmployee(EmployeeDto employeeDto) {

        return employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
    }

    @Override
    public List<EmployeeDto> getALlEmployees() {

        Collection<Employee> employeeCollection = employeeRepository.findAll();

        if (employeeCollection.isEmpty()) {
            logger.info("getALlEmployees(). Retrieved an empty List.");
            return Collections.emptyList();
        }

        return employeeMapper.employeeListToEmployeeDtoList(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto findEmployeeById(Long employeeId) {

        Optional<Employee> optionalEmployee = Optional.of(employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.warn("{}, {}! An exception occurred!",
                            "findEmployeeById().", "Employee with id: " + employeeId + " cannot be found because it does not exist.",
                            new NotFoundException("Employee Id: " + employeeId + " cannot be found because it does not exist."));

                    throw new NotFoundException("Employee Id: " + employeeId + " cannot be found because it does not exist.");
                }));

        return employeeMapper.employeeToEmployeeDto(optionalEmployee.get());
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        final String action = "deleted";
        final String methodName = "deleteEmployee()";
        checkIfEmployeeExistsById(employeeId, methodName, action);

        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Employee updateEmployeeById(Long employeeId, EmployeeDto employeeDto) {

        final String action = "updated";
        final String methodName = "updateEmployeeById()";
        checkIfEmployeeExistsById(employeeId, methodName, action);

        return employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto));
    }

    private void checkIfEmployeeExistsById(Long employeeId, String methodName, String action) {

        boolean exists = employeeRepository.existsById(employeeId);

        if (!exists) {

            logger.warn("{}, {}! An exception occurred!",
                    "" + methodName + ".", "Employee with id: " + employeeId + " cannot be " + action + " because it does not exist.",
                    new NotFoundException("Employee id: " + employeeId + " cannot be " + action + " because it does not exist."));

            throw new NotFoundException("Employee id: " + employeeId + " cannot be " + action + " because it does not exist.");
        }
    }
}
