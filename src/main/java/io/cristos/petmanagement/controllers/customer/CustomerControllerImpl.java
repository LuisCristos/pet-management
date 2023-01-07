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

    private final CustomerServiceImpl personService;

    public CustomerControllerImpl(CustomerServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Customer> saveNewPerson(@RequestBody @Valid CustomerRequest customerRequest) {
        return new ResponseEntity<>(personService.savePerson(customerRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findPersonById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(personService.findById(id));
    }


    // TO_LC: delete, update with boolean.
}
