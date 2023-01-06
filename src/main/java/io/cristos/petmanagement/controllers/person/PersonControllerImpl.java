package io.cristos.petmanagement.controllers.person;

import io.cristos.petmanagement.dtos.person.PersonRequest;
import io.cristos.petmanagement.exceptions.UserNotFoundException;
import io.cristos.petmanagement.models.person.Person;
import io.cristos.petmanagement.services.person.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonControllerImpl implements PersonController {

    private final PersonServiceImpl personService;

    public PersonControllerImpl(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Person> saveNewPerson(@RequestBody @Valid PersonRequest personRequest) {
        return new ResponseEntity<>(personService.savePerson(personRequest), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(personService.findById(id));
    }
}
