package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class HouseJsonSerializer {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * To serialize House in JSON string
     *
     * @param house House
     * @return JSON-string
     * @throws JsonProcessingException if wrong string is set.
     */
    public static String serializeToString(House house) throws JsonProcessingException {
        return mapper.writeValueAsString(house);
    }

    /**
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    public static House deserializeFromString(String json) throws JsonProcessingException {
        return mapper.readValue(json, House.class);
    }
}