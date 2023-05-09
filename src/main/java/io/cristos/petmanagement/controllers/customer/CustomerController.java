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

//        logger.info(customerRequestDto + " saved to database.");
        logger.info("Customer: {} saved to database.", customerRequestDto);

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
            @RequestParam @Min(value = 0, message = "{validation.min.requestparam.pagenumber}") int pageNumber,
            @RequestParam @Min(value = 1, message = "{validation.min.requestparam.pagesize}") int pageSize,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) LocalDate birthdate) {

//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)

        logger.info("Retrieved all customers:");

        return ResponseEntity.ok(customerService.getAllCustomersPageSortFilter(pageNumber,
                pageSize, direction, orderBy, searchValue, birthdate));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> findCustomerById(@PathVariable(name = "customerId")
                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                Long customerId) {

        logger.info("Find CustomerCsv with customerId." + customerId);

        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> deleteCustomerById(@PathVariable(name = "customerId")
                                                                  @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                  Long customerId) {

        customerService.deleteCustomerById(customerId);

        logger.info("Deleted customer with customerId: " + customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
