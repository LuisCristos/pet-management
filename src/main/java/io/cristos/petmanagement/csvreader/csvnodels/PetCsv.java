package io.cristos.petmanagement.csvreader.csvnodels;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;

public class PetCsv {

    @CsvBindByName(column = "petname")
    private String name;
    @CsvBindByName(column = "sex")
    private String gender;
    @CsvBindByName(column = "bornAt")
    @CsvDate(value = "yyyy-MM-dd")
    private LocalDate bornAt;
    @CsvBindByName(column = "species")
    private String species;

    public PetCsv(String name, String gender, LocalDate bornAt, String species) {
        this.name = name;
        this.gender = gender;
        this.bornAt = bornAt;
        this.species = species;
    }

    public PetCsv() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBornAt() {
        return bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
