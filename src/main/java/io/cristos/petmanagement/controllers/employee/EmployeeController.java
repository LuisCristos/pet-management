package io.cristos.petmanagement.controllers.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
import io.cristos.petmanagement.models.employee.Employee;
import io.cristos.petmanagement.services.employee.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@Validated
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@Valid
                                                    @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.saveEmployee(employeeDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/employeeId")
                .buildAndExpand(employee.getId())
                .toUri();

        logger.info(employeeDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        logger.info("getAllEmployees. Retrieved all Employees.");

        return ResponseEntity.ok(employeeService.getALlEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable(name = "employeeId")
                                                        @NotNull
                                                        @Min(1) Long employeeId) {
        logger.info("Find employee by id: " + employeeId);

        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable(name = "employeeId")
                                                          @Min(1)
                                                          @NotNull Long employeeId) {

        employeeService.deleteEmployee(employeeId);

        logger.info("Deleted employee by id" + employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable(name = "employeeId")
                                                          @Min(1)
                                                          @NotNull
                                                          Long employeeId,
                                                          @Valid
                                                          @RequestBody EmployeeDto employeeDto) {

        employeeService.updateEmployeeById(employeeId, employeeDto);

        logger.info("Updated employee by id: " + employeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
