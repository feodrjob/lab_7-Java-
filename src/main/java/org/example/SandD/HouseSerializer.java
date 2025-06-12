package org.example.SandD;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.Flat;
import org.example.House;

import java.io.IOException;

public class HouseSerializer extends StdSerializer<House> {
    public HouseSerializer() { super(House.class); }

    @Override
    public void serialize(House house, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("houseNumber", house.getHouseNumber());
        gen.writeStringField("address", house.getAderess());
        gen.writeObjectField("owners", house.getOwners());
        gen.writeArrayFieldStart("flatsList");
        for (Flat flat : house.getFlatsList()) {
            provider.defaultSerializeValue(flat, gen);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}