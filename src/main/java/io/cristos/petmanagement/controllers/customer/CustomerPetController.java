package io.cristos.petmanagement.controllers.customer;

import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.services.customer.CustomerPetService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers")
@Validated
public class CustomerPetController {

    private final Logger logger = LoggerFactory.getLogger(CustomerPetController.class);

    private final CustomerPetService customerPetService;

    @Autowired
    public CustomerPetController(CustomerPetService customerPetService) {
        this.customerPetService = customerPetService;
    }

    @GetMapping("/{customerId}/pets")
    public ResponseEntity<CustomerPetResponseDto> getCustomerWithAllPets(@PathVariable
                                                                         @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                         Long customerId) {
        logger.info("Retrieve customer with all pets.");

        return ResponseEntity.ok(customerPetService.getCustomerWithAllPets(customerId));
    }
}
