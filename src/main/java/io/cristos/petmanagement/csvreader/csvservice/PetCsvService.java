package io.cristos.petmanagement.csvreader.csvservice;

import io.cristos.petmanagement.csvreader.csvnodels.PetCsv;

import java.io.File;
import java.util.List;

public interface PetCsvService {

    List<PetCsv> convertCsv(File csvFile);
}
