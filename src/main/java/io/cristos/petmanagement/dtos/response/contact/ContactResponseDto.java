package io.cristos.petmanagement.dtos.response.contact;

import java.util.ArrayList;
import java.util.List;

public class ContactResponseDto {
    private Long contactId;
    private String street;
    private int houseNumber;
    private String city;
    private String zipCode;
    private List<String> phoneNumberList = new ArrayList<>();
    private String email;

    public ContactResponseDto() {
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
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

    public List<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumberList.add(phoneNumber);
    }

    public void removePhoneNumber(String phoneNumber) {
        this.phoneNumberList.remove(phoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactResponseDto{" +
                "contactId=" + contactId +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumberList=" + phoneNumberList +
                ", email='" + email + '\'' +
                '}';
    }
}
