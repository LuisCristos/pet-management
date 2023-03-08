package io.cristos.petmanagement.bootstrap;

import io.cristos.petmanagement.csvreader.csvnodels.ContactCsv;
import io.cristos.petmanagement.csvreader.csvnodels.CustomerCsv;
import io.cristos.petmanagement.csvreader.csvservice.ContactCsvService;
import io.cristos.petmanagement.csvreader.csvservice.CustomerCsvService;
import io.cristos.petmanagement.models.contact.Contact;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.repositories.contact.ContactRepository;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
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

    @Autowired
    public BootstrapData(CustomerRepository customerRepository, CustomerCsvService customerCsvService, ContactCsvService contactCsvService, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.customerCsvService = customerCsvService;
        this.contactCsvService = contactCsvService;
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        for (Customer customer : loadCustomerCsv()) {
            customerRepository.save(customer);
        }

        for (Contact contact : loadContactCsv()) {
            contactRepository.save(contact);
        }

        List<Customer> customerList = customerRepository.findAll();
        List<Contact> contactList = contactRepository.findAll();

        Customer customer;
        Contact contact;

        for (int i = 0; i < customerList.size(); i++) {

            customer = customerList.get(i);

            contact = contactList.get(i);

            customer.setContact(contact);

            customerRepository.save(customer);
        }
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
