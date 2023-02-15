package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerControllerImpl implements CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerImpl.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {

        Customer customer = customerService.saveCustomer(customerDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(customer.getId())
                .toUri();

        logger.info(customerDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {

        logger.info("Retrieved all customers.");

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable(name = "customerId")
                                                        @Min(1)
                                                        @NotNull Long customerId) {

        logger.info("Find Customer with customerId." + customerId);

        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @Override
    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable(name = "customerId")
                                                          @Min(1)
                                                          @NotNull Long customerId) {

        customerService.deleteCustomerById(customerId);

        logger.info("Deleted customer with customerId: " + customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "customerId")
                                                      @Min(1)
                                                      @NotNull Long customerId,
                                                      @Valid
                                                      @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerId, customerDto);

        logger.info("Updated customer with customerId: " + customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
