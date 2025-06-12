
package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.SandD.FlatDeserializer;
import org.example.SandD.FlatSerializer;
import org.example.SandD.HouseDeserializer;
import org.example.SandD.HouseSerializer;
import org.example.SandD.PersonDeserializer;
import org.example.SandD.PersonSerializer;

public class App {
    public static void main(String[] args) {
        SimpleModule module = new SimpleModule();

        module.addDeserializer(House.class, new HouseDeserializer());
        module.addDeserializer(Flat.class, new FlatDeserializer());
        module.addDeserializer(Person.class, new PersonDeserializer());

        module.addSerializer(House.class, new HouseSerializer());
        module.addSerializer(Flat.class, new FlatSerializer());
        module.addSerializer(Person.class, new PersonSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

    }
}