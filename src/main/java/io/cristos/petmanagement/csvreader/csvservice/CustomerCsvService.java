package io.cristos.petmanagement.csvreader.csvservice;

import io.cristos.petmanagement.csvreader.csvnodels.CustomerCsv;

import java.io.File;
import java.util.List;

public interface CustomerCsvService {
    List<CustomerCsv> convertCsv(File csvFile);
}
