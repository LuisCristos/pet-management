package io.cristos.petmanagement.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");

    private String shortName;

    public static Gender fromShortName(String shortName) {

        switch (shortName) {

            case "Female":
                return Gender.FEMALE;

            case "Male":
                return Gender.MALE;

            case "Other":
                return Gender.OTHER;

            default:
                throw new IllegalArgumentException("Gender [" + shortName + "] not supported. " +
                        "Allowed types are Male, Female, Other.");
        }
    }
}
