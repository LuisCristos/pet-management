package io.cristos.petmanagement.services.person;

import io.cristos.petmanagement.dtos.person.PersonRequest;
import io.cristos.petmanagement.exceptions.UserNotFoundException;
import io.cristos.petmanagement.models.person.Person;

import java.util.List;

public interface PersonService {

    Person savePerson(PersonRequest personRequest);

    List<Person> getAllPersons();

    Person findById(Long id) throws UserNotFoundException;
}
