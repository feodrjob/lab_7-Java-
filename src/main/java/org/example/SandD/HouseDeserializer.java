package org.example.SandD;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.Flat;
import org.example.House;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseDeserializer extends StdDeserializer<House> {
    public HouseDeserializer() { super(House.class); }

    @Override
    public House deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String houseNumber = null, address = null;
        Person owners = null;
        List<Flat> flats = new ArrayList<>();
        while (p.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = p.getCurrentName();
            if ("houseNumber".equals(fieldName)) {
                p.nextToken();
                houseNumber = p.getText();
            } else if ("address".equals(fieldName)) {
                p.nextToken();
                address = p.getText();
            } else if ("owners".equals(fieldName)) {
                p.nextToken();
                owners = p.readValueAs(Person.class);
            } else if ("flatsList".equals(fieldName)) {
                p.nextToken();
                while (p.nextToken() != JsonToken.END_ARRAY) {
                    flats.add(p.readValueAs(Flat.class));
                }
            }
        }
        return new House(houseNumber, address, owners, flats.toArray(flats.toArray(new Flat[0])));
    }
}