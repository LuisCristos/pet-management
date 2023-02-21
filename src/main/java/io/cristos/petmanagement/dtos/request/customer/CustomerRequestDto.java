package io.cristos.petmanagement.dtos.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CustomerRequestDto {
    @NotBlank(message = "{validation.notblank.firstname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String firstName;
    @NotBlank(message = "{validation.notblank.lastname}")
    @Size(min = 2, max = 100, message = "{validation.size.input}")
    private String lastName;
    @NotNull(message = "{validation.notnull.bornat}")
    @Past(message = "{validation.past}")
    private LocalDate bornAt;
    @NotBlank(message = "{validation.notblank.gender}")
    private String gender;

    public CustomerRequestDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBornAt() {
        return bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
