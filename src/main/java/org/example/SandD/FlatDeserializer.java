package org.example.SandD;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.Flat;
import org.example.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer extends StdDeserializer<Flat> {
    public FlatDeserializer() {
        super(Flat.class);
    }

    @Override
    public Flat deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int number = 0;
        double square = 0;
        List<Person> dataList = new ArrayList<>();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = p.getCurrentName();
            if ("number".equals(fieldName)) {
                p.nextToken();
                number = p.getIntValue();
            } else if ("square".equals(fieldName)) {
                p.nextToken();
                square = p.getDoubleValue();
            } else if ("data".equals(fieldName)) {
                p.nextToken();
                if (p.currentToken() == JsonToken.START_ARRAY) {
                    while (p.nextToken() != JsonToken.END_ARRAY) {
                        dataList.add(p.readValueAs(Person.class));
                    }
                }
            }
        }
        return new Flat(number, square, dataList.toArray(new Person[0]));
    }
}