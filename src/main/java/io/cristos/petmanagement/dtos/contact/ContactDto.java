package io.cristos.petmanagement.dtos.contact;

import jakarta.validation.constraints.NotBlank;

public class ContactDto {

    private Long id;
    @NotBlank(message = "Street is required.")
    private String street;
    //    @NotEmpty(message = "House number is required.")
    private int houseNumber;
    @NotBlank(message = "City is required.")
    private String city;
    private String zipCode;
    @NotBlank(message = "Mobile phone number is required.")
    private String mobileNumber;
    private String phoneNumber;
    @NotBlank(message = "Email is required.")
//    @Email(message = "Please enter email in this format valid@valid.com.",
//            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    public ContactDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
