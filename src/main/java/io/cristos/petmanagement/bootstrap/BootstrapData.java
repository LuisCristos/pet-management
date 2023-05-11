package io.cristos.petmanagement.bootstrap;

import io.cristos.petmanagement.csvreader.csvnodels.ContactCsv;
import io.cristos.petmanagement.csvreader.csvnodels.CustomerCsv;
import io.cristos.petmanagement.csvreader.csvnodels.PetCsv;
import io.cristos.petmanagement.csvreader.csvservice.ContactCsvService;
import io.cristos.petmanagement.csvreader.csvservice.CustomerCsvService;
import io.cristos.petmanagement.csvreader.csvservice.PetCsvService;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.models.pet.Pet;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import io.cristos.petmanagement.repositories.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CustomerCsvService customerCsvService;
    private final ContactCsvService contactCsvService;
    private final ContactRepository contactRepository;
    private final PetCsvService petCsvService;
    private final PetRepository petRepository;

    @Autowired
    public BootstrapData(CustomerRepository customerRepository, CustomerCsvService customerCsvService, ContactCsvService contactCsvService, ContactRepository contactRepository, PetCsvService petCsvService, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.customerCsvService = customerCsvService;
        this.contactCsvService = contactCsvService;
        this.contactRepository = contactRepository;
        this.petCsvService = petCsvService;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        for (Pet pet : loadPetCsv()) {
            petRepository.save(pet);
        }

        for (Customer customer : loadCustomerCsv()) {
            customerRepository.save(customer);
        }

        for (Contact contact : loadContactCsv()) {
            contactRepository.save(contact);
        }

        setContactForCustomer();
        setPetForCustomer();

    }

    private void setPetForCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<Pet> petList = petRepository.findAll();

        Customer customer;
        Pet pet;

        for (int i = 0; i < customerList.size(); i++) {
            customer = customerList.get(i);

            Random random = new Random();
            int randomValue = random.nextInt(4);

            for (int j = 0; j <= randomValue; j++) {

                int randomNext = random.nextInt(500);
                pet = petList.get(randomNext);

                pet.setCustomer(customer);
                customer.addPet(pet);
                customerRepository.save(customer);
            }
        }
    }

    private void setContactForCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<Contact> contactList = contactRepository.findAll();

        Customer customer = null;
        Contact contact;

        for (int i = 0; i < customerList.size(); i++) {

            customer = customerList.get(i);

            contact = contactList.get(i);

            customer.setContact(contact);
        }

        customerRepository.save(customer);

    }

    private List<Pet> loadPetCsv() throws FileNotFoundException {

        File petCsvFile = ResourceUtils.getFile("classpath:csvdata/pet/pet-500.csv");

        List<PetCsv> petCsvList = petCsvService.convertCsv(petCsvFile);

        List<Pet> petList = new ArrayList<>();

        for (PetCsv petCsv : petCsvList) {

            Pet pet = new Pet();

            pet.setName(petCsv.getName());

            if (petCsv.getGender().equals("Male")) {
                pet.setGender(Gender.MALE);
            } else if (petCsv.getGender().equals("Female")) {
                pet.setGender(Gender.FEMALE);
            } else {
                pet.setGender(Gender.OTHER);
            }

            pet.setBornAt(petCsv.getBornAt());
            pet.setSpecies(petCsv.getSpecies());

            petList.add(pet);
        }

        return petList;

    }

    private List<Contact> loadContactCsv() throws FileNotFoundException {

        File contactCsvFile = ResourceUtils.getFile("classpath:csvdata/contact/contacts.csv");

        List<ContactCsv> contactCsvList = contactCsvService.convertCsv(contactCsvFile);

        List<Contact> contactList = new ArrayList<>();

        for (ContactCsv contactCsv : contactCsvList) {

            Contact contact = new Contact();

            contact.setZipCode(contactCsv.getZipCode());
            contact.setCity(contactCsv.getCity());
            contact.setStreet(contactCsv.getStreet());

            if (contactCsv.getHouseNumber() <= 0) {
                Random random = new Random();

                int houseNumberRandom = random.nextInt(1000) + 1;

                contact.setHouseNumber(houseNumberRandom);
            }

            contact.setEmail(contactCsv.getEmail());

            if (contactCsv.getPhoneNumber1() != null) {
                contact.addPhoneNumber(contactCsv.getPhoneNumber1());
            }
            if (contactCsv.getPhoneNumber2() != null) {
                contact.addPhoneNumber(contactCsv.getPhoneNumber2());
            }
            if (contactCsv.getPhoneNumber3() != null) {
                contact.addPhoneNumber(contactCsv.getPhoneNumber3());
            }

            contactList.add(contact);
        }

        return contactList;
    }

    private List<Customer> loadCustomerCsv() throws FileNotFoundException {

        File csvFile = ResourceUtils.getFile("classpath:csvdata/customer/customers.csv");

        List<CustomerCsv> customerCsvList = customerCsvService.convertCsv(csvFile);

        List<Customer> customerList = new ArrayList<>();

        for (CustomerCsv customerCsv : customerCsvList) {

            Customer customer = new Customer();

            customer.setGender(customerCsv.getGender());
            customer.setBornAt(customerCsv.getBornAt());

            int age = Period.between(customerCsv.getBornAt(), LocalDate.now()).getYears();

            if (age <= 18) {

                customer.setGender(Gender.OTHER);
                customer.setBornAt(customerCsv.getBornAt().minusYears(20));

            }

            customer.setFirstName(customerCsv.getFirstName());
            customer.setLastName(customerCsv.getLastName());

            customerList.add(customer);
        }

        return customerList;
    }
}
