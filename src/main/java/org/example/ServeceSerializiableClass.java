package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServeceSerializiableClass {
    public static void serializePerson(Person person, String filename) throws IOException{
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        Files.newOutputStream(Paths.get(filename))))){
            out.writeObject(person);
        }
    }

    public static void serializeFlat (Flat flat, String filename) throws IOException{
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        Files.newOutputStream(Paths.get(filename))))){
            out.writeObject(flat);
        }
    }

    public static void serializeHouse (House house, String filename) throws IOException{
        try (ObjectOutput out = new ObjectOutputStream(
                new BufferedOutputStream(
                        Files.newOutputStream(Paths.get(filename))))){
            out.writeObject(house);
        }
    }
}
