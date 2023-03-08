package io.cristos.petmanagement.csvreader.csvservice;

import com.opencsv.bean.CsvToBeanBuilder;
import io.cristos.petmanagement.csvreader.csvnodels.ContactCsv;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class ContactCsvServiceImpl implements ContactCsvService {
    @Override
    public List<ContactCsv> convertCsv(File csvFile) {

        try {

            List<ContactCsv> contactCsvs = new CsvToBeanBuilder<ContactCsv>(new FileReader(csvFile))
                    .withType(ContactCsv.class)
                    .build().parse();
            return contactCsvs;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
