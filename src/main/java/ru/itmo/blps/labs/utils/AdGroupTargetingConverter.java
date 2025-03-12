package ru.itmo.blps.labs.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;

@Converter
public class AdGroupTargetingConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SERVER_ERROR);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(
                dbData, new TypeReference<>() {
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ExceptionEnum.SERVER_ERROR);
        }
    }
}
