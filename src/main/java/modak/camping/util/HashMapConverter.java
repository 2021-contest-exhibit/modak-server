package modak.camping.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>,String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(json, Map.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}