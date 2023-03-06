package io.cristos.petmanagement.csvreader.csvservice;

import com.opencsv.bean.CsvToBeanBuilder;
import io.cristos.petmanagement.csvreader.csvnodels.CustomerCsv;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class CustomerCsvServiceImpl implements CustomerCsvService {


    @Override
    public List<CustomerCsv> convertCsv(File csvFile) {

        try {
            List<CustomerCsv> customerCsvs = new CsvToBeanBuilder<CustomerCsv>(new FileReader(csvFile))
                    .withType(CustomerCsv.class)
                    .build().parse();
            return customerCsvs;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
