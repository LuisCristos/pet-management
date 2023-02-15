package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerController {

    ResponseEntity<CustomerDto> saveCustomer(@Valid
                                             @RequestBody CustomerDto customerDto);

    ResponseEntity<List<CustomerDto>> getAllCustomers();

    ResponseEntity<CustomerDto> findCustomerById(@PathVariable(name = "customerId")
                                                 @Min(1)
                                                 @NotNull Long customerId);

    ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable(name = "customerId")
                                                   @Min(1)
                                                   @NotNull Long customerId);

    ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "customerId")
                                               @Min(1)
                                               @NotNull Long customerId,
                                               @Valid
                                               @RequestBody CustomerDto customerDto);

}
