package de.jhulsch.library.persistence.converter;

import de.jhulsch.library.common.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender,String> {


    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return gender.toString();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        return Gender.valueOf(s);
    }
}
