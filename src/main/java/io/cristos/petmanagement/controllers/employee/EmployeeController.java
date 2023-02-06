package io.cristos.petmanagement.controllers.employee;

import io.cristos.petmanagement.dtos.employee.EmployeeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeController {

    ResponseEntity<EmployeeDto> saveEmployee(@Valid
                                             @RequestBody EmployeeDto employeeDto);

    ResponseEntity<List<EmployeeDto>> getAllEmployees();

    ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable(name = "employeeId")
                                                 @Min(1)
                                                 @NotNull Long employeeId);

    ResponseEntity<EmployeeDto> deleteEmployeeById(@PathVariable(name = "employeeId")
                                                   @Min(1)
                                                   @NotNull Long employeeId);

    ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable(name = "employeeId")
                                                   @Min(1)
                                                   @NotNull
                                                   Long employeeId,
                                                   @Valid
                                                   @RequestBody EmployeeDto employeeDto);
}
