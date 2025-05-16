package org.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.IOStreamDemo.saveHouseToCSV;
import static org.junit.jupiter.api.Assertions.*;

class HouseCSVWriterTest {

    @Test
    void testSaveHouseToCSV(@TempDir Path tempDir) throws IOException {
        Person owner = new Person("Иванов", "Сидор", "Петрович", "01.01.1970");
        Person[] flat1Owners = {new Person("Петров", "Алексей", "Владимирович", "15.05.1985")};
        Person[] flat2Owners = {
                new Person("Сидорова", "Ольга", "Михайловна", "20.08.1990"),
                new Person("Васечкин", "Иван", "Петрович", "10.03.1975")
        };

        Flat[] flats = {
                new Flat(1, 40.0, flat1Owners),
                new Flat(2, 65.0, flat2Owners)
        };

        House house = new House("12345", "г. Омск, пр. Мира, 321", owner, flats);

        saveHouseToCSV(house);

    }

    @Test
    void testSaveHouseToCSV_EmptyFlats(@TempDir Path tempDir) throws IOException {
        Person owner = new Person("Иванов", "Иван", "Иванович", "01.01.1970");
        House house = new House("54321", "г. Москва, ул. Ленина, 1", owner, new Flat[0]);

        saveHouseToCSV(house);

        Path filePath = Path.of("house_54321.csv");
        String content = Files.readString(filePath);

        assertTrue(content.contains("Кадастровый номер: 54321"));
        assertTrue(content.contains("Данные о квартирах"));
        assertTrue(content.contains("№;Площадь, кв. м;Владельцы"));
    }
}