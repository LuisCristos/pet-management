package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerDto;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@RequestMapping("/customers")
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
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        logger.info(customerDto + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {

        logger.info("getAllCustomers(). Retrieved all customers.");

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable
                                                        @Positive
                                                        @NotNull Long customerId) {

        try {
            logger.info("Retrieved customer with customerId: " + customerId);

            return ResponseEntity.ok(customerService.findCustomerById(customerId));

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "findCustomerById().", "Customer with customerId: " + customerId + " cannot be found because it does not exist.",
                    new NotFoundException("Customer with customerId: " + customerId + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomerById(@PathVariable
                                                          @Positive
                                                          @NotNull Long customerId) {

        try {

            customerService.deleteCustomerById(customerId);

            logger.info("Deleted customer with customerId: " + customerId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteCustomerById().", "Customer with customerId: " + customerId + " cannot be deleted because it does not exist.",
                    new NotFoundException("Customer with customerId: " + customerId + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{customerID}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerID,
                                                      @Valid
                                                      @RequestBody CustomerDto customerDto) {
        try {
            customerService.updateCustomer(customerID, customerDto);

            logger.info("Updated customer with customerID: " + customerID);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateCustomer().", "Customer with customerID: " + customerID + " cannot be updated because it does not exist.",
                    new NotFoundException("Customer with customerID: " + customerID + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
