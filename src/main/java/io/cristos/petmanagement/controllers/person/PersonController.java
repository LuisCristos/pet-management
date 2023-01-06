package io.cristos.petmanagement.controllers.person;


import io.cristos.petmanagement.dtos.person.PersonRequest;
import io.cristos.petmanagement.exceptions.UserNotFoundException;
import io.cristos.petmanagement.models.person.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonController {

    ResponseEntity<Person> saveNewPerson( PersonRequest personRequest);

    ResponseEntity<List<Person>> getAllPersons();

    ResponseEntity<Person> findPersonById(Long id) throws UserNotFoundException;
}
