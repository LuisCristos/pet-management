package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.customer.CustomerRequest;
import io.cristos.petmanagement.exceptions.NotFoundException;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.saveCustomer(customerRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {

        customerService.deleteCustomerById(id);
    }


}
