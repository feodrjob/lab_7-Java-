package org.example.SandD;

// PersonSerializer.java
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.Person;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {
    public PersonSerializer() { super(Person.class); }

    @Override
    public void serialize(Person person, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("fullName", person.getLastname() + " " + person.getFirstname() + " " + person.getSurname());
        gen.writeEndObject();
    }
}