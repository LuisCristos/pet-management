package io.cristos.petmanagement.csvreader.csvnodels;

import com.opencsv.bean.CsvBindByName;

public class ContactCsv {

    @CsvBindByName(column = "address")
    private String street;
    private int houseNumber;
    @CsvBindByName(column = "city")
    private String city;
    @CsvBindByName(column = "zip")
    private String zipCode;
    @CsvBindByName(column = "email")
    private String email;
    @CsvBindByName(column = "phone")
    private String phoneNumber1;
    @CsvBindByName(column = "phone1")
    private String phoneNumber2;
    private String phoneNumber3;

    public ContactCsv(String street, int houseNumber, String city, String zipCode, String email,
                      String phoneNumber1, String phoneNumber2, String phoneNumber3) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.email = email;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.phoneNumber3 = phoneNumber3;
    }

    public ContactCsv() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }
}
