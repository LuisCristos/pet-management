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
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerControllerImpl implements CustomerController {

    private final CustomerServiceImpl customerService;

    public CustomerControllerImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

        return new ResponseEntity<>(customerService.saveCustomer(customerRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> findCustomerById(@PathVariable Long id) {

        try {

            return ResponseEntity.ok(customerService.findCustomerById(id));

        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id) {

        try {

            customerService.deleteCustomerById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,
                                                   @RequestBody CustomerRequest customerRequest) {
        try {
            customerService.updateCustomer(id, customerRequest);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//
//    @Override
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer> updateCustomer(
//            @PathVariable Long id,
//            @RequestParam() @Size(min = 2, max = 255, message = "Firstname must be between 2 and 255 characters long") String firstName,
//            @RequestParam() String lastName,
//            @RequestParam() LocalDate dateOfBirth
//    ) {
//
//        try {
//            customerService.updateCustomer(id, firstName, lastName, dateOfBirth);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        } catch (NotFoundException e) {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
