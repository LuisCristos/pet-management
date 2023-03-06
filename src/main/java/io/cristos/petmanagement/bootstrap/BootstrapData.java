package io.cristos.petmanagement.bootstrap;

import io.cristos.petmanagement.csvreader.csvnodels.CustomerCsv;
import io.cristos.petmanagement.csvreader.csvservice.CustomerCsvService;
import io.cristos.petmanagement.models.customer.Customer;
import io.cristos.petmanagement.models.enums.Gender;
import io.cristos.petmanagement.repositories.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final CustomerCsvService customerCsvService;

    @Autowired
    public BootstrapData(CustomerRepository customerRepository, CustomerCsvService customerCsvService) {
        this.customerRepository = customerRepository;
        this.customerCsvService = customerCsvService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomerCsv();
    }

    private void loadCustomerCsv() throws FileNotFoundException {

        File csvFile = ResourceUtils.getFile("classpath:csvdata/customer/customers.csv");

        List<CustomerCsv> customerCsvList = customerCsvService.convertCsv(csvFile);
        customerCsvList.forEach(customerCsv -> {
            Gender gender = switch (customerCsv.getGender()) {
                case "Male" -> Gender.MALE;
                case "Female" -> Gender.FEMALE;
                default -> Gender.OTHER;
            };

            Customer customer = new Customer();

            customer.setFirstName(customerCsv.getFirstName());
            customer.setLastName(customerCsv.getLastName());
            customer.setBornAt(customerCsv.getBornAt());
            customer.setGender(gender);

            customerRepository.save(customer);
        });
    }
}
