package io.cristos.petmanagement.controllers.employee;

import io.cristos.petmanagement.dtos.request.employee.EmployeeRequestDto;
import io.cristos.petmanagement.dtos.response.employee.EmployeeResponseDto;
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
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@Valid
                                                            @RequestBody EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeService.saveEmployee(employeeRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/employeeId")
                .buildAndExpand(employee.getId())
                .toUri();

        logger.info(employeeRequestDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {

        logger.info("getAllEmployees. Retrieved all Employees.");

        return ResponseEntity.ok(employeeService.getALlEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> findEmployeeById(@PathVariable(name = "employeeId")
                                                                @NotNull
                                                                @Min(1) Long employeeId) {
        logger.info("Find employee by id: " + employeeId);

        return ResponseEntity.ok(employeeService.findEmployeeById(employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployeeById(@PathVariable(name = "employeeId")
                                                                  @Min(1)
                                                                  @NotNull Long employeeId) {

        employeeService.deleteEmployee(employeeId);

        logger.info("Deleted employee by id" + employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeById(@PathVariable(name = "employeeId")
                                                                  @Min(1)
                                                                  @NotNull
                                                                  Long employeeId,
                                                                  @Valid
                                                                  @RequestBody EmployeeRequestDto employeeRequestDto) {

        employeeService.updateEmployeeById(employeeId, employeeRequestDto);

        logger.info("Updated employee by id: " + employeeId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
