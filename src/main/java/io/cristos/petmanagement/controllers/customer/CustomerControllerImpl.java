package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerServiceImpl;
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
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerImpl.class);
    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<CustomerRequest> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

        // TODO_LC: Question.  @Valid enough for null check ? Should i outsource the is null check in utils?
        boolean isNull = Objects.isNull(customerRequest);

        if (isNull) {

            logger.warn("{}, {}! An exception occurred!",
                    "saveCustomer().", "Received value is null.",
                    new IllegalStateException("CustomerRequest is null."));

            throw new IllegalStateException("CustomerRequest is null.");
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
    public ResponseEntity<List<CustomerRequest>> getAllCustomers() {

        logger.info("getAllCustomers(). Retrieved all customers.");

        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerRequest> findCustomerById(@PathVariable
                                                            @Positive
                                                            @NotNull Long id) {

        try {
            logger.info("Retrieved customer with id: " + id);

            return ResponseEntity.ok(customerService.findCustomerById(id));

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "findCustomerById().", "Customer with id: " + id + " cannot be found because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerRequest> deleteCustomerById(@PathVariable
                                                              @Positive
                                                              @NotNull Long id) {

        try {

            customerService.deleteCustomerById(id);

            logger.info("Deleted customer with id: " + id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "deleteCustomerById().", "Customer with id: " + id + " cannot be deleted because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CustomerRequest> updateCustomer(@PathVariable Long id,
                                                          @Valid
                                                          @RequestBody CustomerRequest customerRequest) {
        try {
            customerService.updateCustomer(id, customerRequest);

            logger.info("Updated customer with id: " + id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NotFoundException e) {

            logger.warn("{}, {}! An exception occurred!",
                    "updateCustomer().", "Customer with id: " + id + " cannot be updated because it does not exist.",
                    new NotFoundException("Customer with id: " + id + " not found"));

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
