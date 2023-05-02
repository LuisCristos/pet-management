package io.cristos.petmanagement.controllers.pet;

import io.cristos.petmanagement.dtos.request.pet.PetRequestDto;
import io.cristos.petmanagement.dtos.response.customer.CustomerPetResponseDto;
import io.cristos.petmanagement.dtos.response.pet.PetResponseDto;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.services.pet.CustomerPetService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/customers/{customerId}/pets")
@Validated
public class CustomerPetController {

    private final Logger logger = LoggerFactory.getLogger(CustomerPetController.class);

    private final CustomerPetService customerPetService;

    @Autowired
    public CustomerPetController(CustomerPetService customerPetService) {
        this.customerPetService = customerPetService;
    }

    @PostMapping
    public ResponseEntity<PetResponseDto> saveCustomerPet(@PathVariable(name = "customerId")
                                                          @Min(value = 1, message = "{validation.min.pathvariable}")
                                                          Long customerId,
                                                          @Valid
                                                          @RequestBody PetRequestDto petRequestDto) {

        Customer customer = customerPetService.savaCustomerPetByCustomerId(customerId, petRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{petId}")
                .buildAndExpand(customer.getPets())
                .toUri();

        logger.info("Saved pet to customer with id: " + customerId);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<CustomerPetResponseDto> findCustomerPetByCustomerId(@PathVariable(name = "customerId")
                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                              Long customerId,
                                                                              @PathVariable(name = "petId")
                                                                              @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                              Long petId) {
        logger.info("Retrieved pet for customer with id: " + customerId);

        return ResponseEntity.ok(customerPetService.findCustomerPetByCustomerId(customerId, petId));
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDto>> getAllCustomerPetsByCustomerId(@PathVariable
                                                                               @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                               Long customerId) {
        logger.info("Retrieve customer with all pets.");

        return ResponseEntity.ok(customerPetService.getAllCustomerPetsByCustomerId(customerId));
    }

    @PutMapping("/{petId}")
    public ResponseEntity<CustomerPetResponseDto> updateCustomerPetByCustomerId(@PathVariable(name = "customerId")
                                                                                @Min(value = 1, message = "{validation.min.pathvariable}")
                                                                                Long customerId,
                                                                                @Valid
                                                                                @RequestBody PetRequestDto petRequestDto,
                                                                                @PathVariable(name = "petId")
                                                                                @Min(value = 1, message = "{validation.min.pathvariable}") Long petId) {
        logger.info("Update pet for customer with id: " + customerId);

        customerPetService.updateCustomerPetByCustomerId(customerId, petRequestDto, petId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
