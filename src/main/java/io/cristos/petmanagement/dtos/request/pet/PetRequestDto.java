package io.cristos.petmanagement.dtos.request.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PetRequestDto {
    @NotBlank(message = "{validation.notblank.name}")
    @Size(min = 2, max = 255, message = "{validation.size.input}")
    private String name;
    @NotNull(message = "{validation.notnull.gender}")
    private String gender;
    @NotNull(message = "{validation.notnull.bornat}")
    @Past(message = "{validation.past}")
    private LocalDate bornAt;
    @NotBlank(message = "{validation.notblank.species}")
    @Size(min = 2, max = 255, message = "{validation.size.input}")
    private String species;

    public PetRequestDto() {
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

    @Override
    public String toString() {
        return "PetRequestDto{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", bornAt=" + bornAt +
                '}';
    }
}
