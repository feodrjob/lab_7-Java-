package org.example.SandD;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.Person;

import java.io.IOException;
public class PersonDeserializer extends StdDeserializer<Person> {
    public PersonDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String lastname = null;
        String firstname = null;
        String surname = null;
        String birthsday = null;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = p.getCurrentName();
            if ("fullName".equals(fieldName)) {
                p.nextToken();
                String[] parts = p.getText().split(" ", 3);
                lastname = parts.length > 0 ? parts[0] : "";//массив строк по проедв\елителю пробелу
                firstname = parts.length > 1 ? parts[1] : "";
                surname = parts.length > 2 ? parts[2] : "";
            } else if ("birthsday".equals(fieldName)) {
                p.nextToken();
                birthsday = p.getText();
            }
        }
        return new Person(lastname, firstname, surname, birthsday);
    }
}