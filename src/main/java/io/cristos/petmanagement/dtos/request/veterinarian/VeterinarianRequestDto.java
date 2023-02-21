package io.cristos.petmanagement.dtos.request.veterinarian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class VeterinarianRequestDto {
        @NotBlank(message = "{validation.notblank.firstname}")
        @Size(min = 2, max = 100, message = "{validation.size.input}")
        private String firstName;
        @NotBlank(message = "{validation.notblank.lastname}")
        @Size(min = 2, max = 100, message = "{validation.size.input}")
        private String lastName;
        @NotNull(message = "{validation.notnull.bornat}")
        @Past(message = "{validation.past}")
        private LocalDate bornAt;
        @NotNull(message = "{validation.notnull.gender}")
        private String gender;
        @NotBlank(message = "{validation.notblank.speciality}")
        @Size(min = 2, max = 100, message = "{validation.size.input}")
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
