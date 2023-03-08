package io.cristos.petmanagement.csvreader.csvservice;

import io.cristos.petmanagement.csvreader.csvnodels.ContactCsv;

import java.io.File;
import java.util.List;

public interface ContactCsvService {

    List<ContactCsv> convertCsv(File csvFile);
}
