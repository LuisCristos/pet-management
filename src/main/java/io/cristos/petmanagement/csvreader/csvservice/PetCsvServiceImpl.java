package io.cristos.petmanagement.csvreader.csvservice;

import com.opencsv.bean.CsvToBeanBuilder;
import io.cristos.petmanagement.csvreader.csvnodels.PetCsv;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class PetCsvServiceImpl implements PetCsvService {
    @Override
    public List<PetCsv> convertCsv(File csvFile) {


        try {
            List<PetCsv> petCsvs = new CsvToBeanBuilder<PetCsv>(new FileReader(csvFile))
                    .withType(PetCsv.class)
                    .build().parse();

            return petCsvs;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
