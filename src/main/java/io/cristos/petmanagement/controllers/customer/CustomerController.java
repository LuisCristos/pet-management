package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.request.customer.CustomerRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.customer.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.time.LocalDate;

@RestController
@RequestMapping("/v1/customers")
@Validated
public class CustomerController {
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
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

        logger.info("Customer: {} saved.", customerRequestDto);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable(name = "customerId")
                                                                  @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                  Long customerId,
                                                              @Valid
                                                                  @RequestBody CustomerRequestDto customerRequestDto) {
        customerService.updateCustomer(customerId, customerRequestDto);

        logger.info("Customer: {} updated.", customerRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponseDto>> getAllCustomersPageSortFilter(

            @SortDefault.SortDefaults({
                    @SortDefault(caseSensitive = false, sort = "firstName", direction = Sort.Direction.ASC),
                    @SortDefault(caseSensitive = false, sort = "lastName", direction = Sort.Direction.ASC)
            })
            Pageable pageable,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) LocalDate birthdate) {

//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

        logger.info("List of Customers retrieved.");

        return ResponseEntity.ok(customerService.getAllCustomersPageSortFilter(pageable,
                searchValue, birthdate));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> findCustomerById(@PathVariable(name = "customerId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long customerId) {

        logger.info("Find Customer with id: {}", customerId);

        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> deleteCustomerById(@PathVariable(name = "customerId")
                                                                  @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                  Long customerId) {

        customerService.deleteCustomerById(customerId);

        logger.info("Customer with id: {} deleted.", customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
