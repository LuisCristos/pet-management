package io.cristos.petmanagement.dtos.request.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class VeterinarianRequestDto {
        @NotBlank(message = "First name is required.")
        @Size(min = 2, max = 100, message = "First name must be between 2 - 100 characters.")
        private String firstName;
        @NotBlank(message = "Last name is required.")
        @Size(min = 2, max = 100, message = "Last name must be between 2 - 100 characters.")
        private String lastName;
        @NotNull(message = "Date of birth is required.")
        @Past(message = "The date of birth must be in the past.")
        private LocalDate bornAt;
        @NotNull(message = "Gender is required.")
        private String gender;
        @NotBlank(message = "Speciality is required.")
        @Size(min = 2, max = 100, message = "Speciality must be between 2 - 100 characters.")
        private String speciality;

        public VeterinarianRequestDto() {
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

        public String getSpeciality() {
                return speciality;
        }

        public void setSpeciality(String speciality) {
                this.speciality = speciality;
        }

        @Override
        public String toString() {
                return "VeterinarianRequestDto{" +
                        "firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", bornAt=" + bornAt +
                        ", gender='" + gender + '\'' +
                        ", speciality='" + speciality + '\'' +
                        '}';
        }
}
