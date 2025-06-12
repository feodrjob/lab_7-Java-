package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.SandD.FlatDeserializer;
import org.example.SandD.HouseDeserializer;
import org.example.SandD.PersonDeserializer;
import org.example.SandD.FlatSerializer;
import org.example.SandD.HouseSerializer;
import org.example.SandD.PersonSerializer;

class HouseJsonSerializerTest {

    @Test
    void testSerializeAndDeserializeHouse() throws JsonProcessingException {
        // Подготовка данных
        Person[] residents1 = {
                new Person("Иванов", "Иван", "Иванович", "01.01.2000"),
                new Person("Сидоров", "Сидор", "Сидорович", "01.01.2000")
        };
        Person[] residents2 = {
                new Person("Петров", "Петр", "Петрович", "01.01.2000")
        };

        Flat[] flats = {
                new Flat(1, 75.5, residents1),
                new Flat(2, 45.2, residents2)
        };

        Person owner = new Person("Главнов", "Главный", "Главнович", "01.01.2000");
        House originalHouse = new House("123", "ул. Ленина, 10", owner, flats);

        // Сериализация и десериализация
        String json = HouseJsonSerializer.serializeToString(originalHouse);
        System.out.println(json); // Для отладки

        House deserializedHouse = HouseJsonSerializer.deserializeFromString(json);

        // Проверки
        assertNotNull(json);
        assertTrue(json.contains("\"houseNumber\" : \"123\""));
        assertTrue(json.contains("\"aderess\" : \"ул. Ленина, 10\""));
        assertTrue(json.contains("\"lastname\" : \"Главнов\""));

        assertEquals(originalHouse.getHouseNumber(), deserializedHouse.getHouseNumber());
        assertEquals(originalHouse.getAderess(), deserializedHouse.getAderess());
        assertNotNull(deserializedHouse.getOwners());
        assertEquals("Главнов", deserializedHouse.getOwners().getLastname());

        assertArrayEquals(originalHouse.getFlatsList(), deserializedHouse.getFlatsList());
    }
    @Test
    void testDeserializeInvalidJson() {
        String invalidJson = "{invalid json}";
        assertThrows(JsonProcessingException.class,
                () -> HouseJsonSerializer.deserializeFromString(invalidJson));
    }

    @Test
    void testSerializeDeserializeEmptyHouse() throws JsonProcessingException {
        // Создаем пустой дом с пустыми массивами вместо null
        House emptyHouse = new House(null, null, null, new Flat[0]);

        String json = HouseJsonSerializer.serializeToString(emptyHouse);
        House deserializedHouse = HouseJsonSerializer.deserializeFromString(json);

        assertNull(deserializedHouse.getHouseNumber());
        assertNull(deserializedHouse.getAderess());
        assertNull(deserializedHouse.getOwners());
        assertNotNull(deserializedHouse.getFlatsList()); // Теперь это не null
        assertEquals(0, deserializedHouse.getFlatsList().length);
    }

    @Test
    void testJsonFormatting() throws JsonProcessingException {
        House house = new House("1", "ул. Тестовая", null, null);
        String json = HouseJsonSerializer.serializeToString(house);

        // Проверяем форматирование JSON
        assertTrue(json.contains("\n"));
        assertTrue(json.contains("  \"houseNumber\""));
    }


}