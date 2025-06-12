package org.example.SandD;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.Flat;
import org.example.Person;
import java.io.IOException;

public class FlatSerializer extends StdSerializer<Flat> {
    public FlatSerializer() {
        super(Flat.class);
    }

    @Override
    public void serialize(Flat flat, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("square", flat.getSquare());
        gen.writeArrayFieldStart("owners");
        for (Person p : flat.getData()) {
            provider.defaultSerializeValue(p, gen);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}