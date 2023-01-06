package io.cristos.petmanagement.services.person;

import io.cristos.petmanagement.dtos.person.PersonRequest;
import io.cristos.petmanagement.exceptions.UserNotFoundException;
import io.cristos.petmanagement.models.person.Person;
import io.cristos.petmanagement.repositories.person.PersonRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person savePerson(PersonRequest personRequest) {

        Person person = new Person();
        person.setId(null);
        person.setFirstName(personRequest.getFirstName());
        person.setLastName(personRequest.getLastName());
        person.setDateOfBirth(personRequest.getDateOfBirth());

        // TODO_LC QUESTION: is here check if null mandatory?

        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPersons() {

        Collection<Person> personCollection = personRepository.findAll();

        if (personCollection.isEmpty()) {
            throw new IllegalStateException("Your List is empty.");
        }

        List<Person> personList = new ArrayList<>(personCollection);

        return personList;
    }

    @Override
    public Person findById(Long id) throws UserNotFoundException {

        Optional<Person> optionalPerson = personRepository.findById(id);

        Person person = optionalPerson.orElseThrow(() -> new UserNotFoundException("Person with id: " +  id + " not found."));

        // TOD_LC: CHECK: if Exception is thrown

        return person;
    }

}
