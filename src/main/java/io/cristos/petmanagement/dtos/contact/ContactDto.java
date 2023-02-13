package io.cristos.petmanagement.dtos.contact;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactDto implements Serializable {

    private Long contactId;
    @NotBlank(message = "Street is required.")
    @Size(min = 2, max = 255, message = "Street must be between 2 -255 characters.")
    private String street;
    @Min(value = 1, message = "House number is required.")
    private int houseNumber;
    @NotBlank(message = "City is required.")
    @Size(min = 2, max = 255, message = "City must be between 2 -255 characters.")
    private String city;
    @NotBlank(message = "Zipcode is required.")
    private String zipCode;
    @ElementCollection
    @CollectionTable(name = "contact_phone_numbers",
            joinColumns = @JoinColumn(name = "contact_id"))
    @NotEmpty(message = "At least one phone number is required")
    private List<@NotBlank(message = "Phone number is required.") String> phoneNumberList = new ArrayList<>();
    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter email in this format valid@valid.com.",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    public ContactDto() {
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

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumberList.add(phoneNumber);
    }

    public void removePhoneNumber(String phoneNumber) {
        this.phoneNumberList.remove(phoneNumber);
    }

    public List<String> getPhoneNumberList() {
        return phoneNumberList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
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
