package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServeceDeserializableClass {
    public static Person deserializePerson(String filename) throws IOException, ClassNotFoundException{
        try(ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        Files.newInputStream(Paths.get(filename))))){
            return (Person) in.readObject();
        }
    }

    public static Flat deserializeFlat(String filename) throws IOException, ClassNotFoundException{
        try (ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        Files.newInputStream(Paths.get(filename))))){
            return (Flat) in.readObject();
        }
    }

    public static House deserializeHouse(String filename) throws IOException, ClassNotFoundException{
        try(ObjectInput in = new ObjectInputStream(
                new BufferedInputStream(
                        Files.newInputStream(Paths.get(filename))))){
            return (House) in.readObject();
        }
    }

}
