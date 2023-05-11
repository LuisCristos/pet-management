package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/customers")
@Validated
@Slf4j
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> saveCustomer(@Valid
                                                            @RequestBody CustomerRequestDto customerRequestDto) {

        Customer customer = customerService.saveCustomer(customerRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{customerId}")
                .buildAndExpand(customer.getId())
                .toUri();

        log.info("Customer: {} saved.", customerRequestDto);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable(name = "customerId")
                                                                  @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                  Long customerId,
                                                              @Valid
                                                                  @RequestBody CustomerRequestDto customerRequestDto) {
        customerService.updateCustomer(customerId, customerRequestDto);

        log.info("Customer: {} updated.", customerRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomersPageSortFilter(
            @SortDefault.SortDefaults({
                    @SortDefault(caseSensitive = false, sort = "firstName", direction = Sort.Direction.ASC),
                    @SortDefault(caseSensitive = false, sort = "lastName", direction = Sort.Direction.ASC)
            })
            Pageable pageable,
            @RequestParam(required = false) String searchValue) {

        log.info("List of Customers retrieved.");

        return ResponseEntity.ok(customerService.getAllCustomersPageSortFilter(pageable, searchValue));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> findCustomerById(@PathVariable(name = "customerId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long customerId) {

        log.info("Find Customer with id: {}", customerId);

        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> deleteCustomerById(@PathVariable(name = "customerId")
                                                                  @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                  Long customerId) {

        customerService.deleteCustomerById(customerId);

        log.info("Customer with id: {} deleted.", customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
