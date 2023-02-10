package io.cristos.petmanagement.utilities.mapper.genderconverter;

import io.cristos.petmanagement.models.enums.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {

        return attribute.getShortName();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {

        return Gender.fromShortName(dbData);
    }
}
