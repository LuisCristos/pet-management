package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerImpl.class);
    private final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

        boolean isNull = Objects.isNull(customerRequest);

        if (isNull) {

            logger.info("RequestBody CustomerRequest is null");

            throw new IllegalStateException("Ist null.");
        }

        Customer customer = customerService.saveCustomer(customerRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        logger.info(customerRequest + " saved to database.");

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        logger.info("getAllCustomers(). Retrieved all customers.");

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> findCustomerById(@PathVariable Long id) {

        try {
            logger.info("findCustomerById(). Retrieved customer by id.");

            return ResponseEntity.ok(customerService.findCustomerById(id));

        } catch (NotFoundException e) {

            logger.info("findCustomerById(). Customer with id: " + id + " cannot be found.");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {

        try {

            customerService.deleteCustomerById(id);


            logger.info("Deleted customer with id: " + id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NotFoundException e) {

            logger.info("deleteCustomerById(). Customer ID: " + id + " cannot be deleted because it does not exist.");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @Valid
                                                   @RequestBody CustomerRequest customerRequest) {
        try {
            customerService.updateCustomer(id, customerRequest);

            logger.info("Updated customer with id: " + id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NotFoundException e) {

            logger.info("updateCustomer(). Customer ID:  " + id + " cannot be updated because it does not exist.");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
