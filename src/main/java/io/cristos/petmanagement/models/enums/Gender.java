package io.cristos.petmanagement.models.enums;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    OTHER("Other");

    private final String shortName;


    Gender(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

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
