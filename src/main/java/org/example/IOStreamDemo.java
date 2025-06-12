package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.*;
import static java.nio.file.Files.newOutputStream;

public class IOStreamDemo {
    public static void writeInArrayBin(int[] items) {
        try (final DataOutputStream dos = new DataOutputStream(newOutputStream(Paths.get("file1.txt")))) {
            for (Integer num : items) {
                dos.writeInt(num);
            }
        } catch (IOException e) {
            System.out.println("Файл не создан");
        }
    }

    public static int[] readAtArrayBin(int n) {
        int[] result = new int[n];
        try (DataInputStream dos = new DataInputStream(Files.newInputStream(Paths.get("file1.txt")))) {
            for (int i = 0; i < n; i++) {
                result[i] = dos.readInt();
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInArrayChar(int[] items) {
        try (final FileWriter dos = new FileWriter("file3.txt")) {
            for (Integer item : items) {
                dos.write(valueOf(item));
                dos.write(' ');
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int[] readAtArrayChar(int n) {
        int[] result = new int[n];
        String[] numbers;
        try (BufferedReader dos = new BufferedReader(Files.newBufferedReader(Paths.get("file3.txt")))) {
            numbers = dos.readLine().split(" ");
            for (int i = 0; i < numbers.length; i++) {
                result[i] = Integer.parseInt(numbers[i]);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static int[] readAtArrayWithPosition(int n, int start) {
        String[] numbers;
        int[] result = new int[n - start];
        try (RandomAccessFile raf = new RandomAccessFile("file1.txt", "r")) {
            raf.seek(start * 4L);
            for (int i = 0; i < result.length; i++) {
                result[i] = raf.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Path> findFilesByExtension(String directory, String extension) {
        try (Stream<Path> path = Files.walk(Paths.get(directory))) {//рекурсивно обходим файлы дирректории
            return path
                    .filter(Files::isRegularFile)//проверям что пришли к обычному файлу а не к дирректории
                    .filter(file -> file.toString().endsWith("." + extension))//
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> findFiles(String directory, String regular) {
        try (Stream<Path> path = Files.walk(Paths.get(directory))) {
            Pattern pattern = Pattern.compile(regular);//проверяем и отпимиищруем чтобы использовать в дальнейшем
            return path.filter(file -> pattern.matcher(file.getFileName().toString())//движок поиска совпадений
                            .matches())
                    .map(Path::toAbsolutePath)// преобразование потока
                    .map(Path::toString)
                    .collect(Collectors.toList());// собираем поток в одну структуру

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveHouseToCSV(House house) {
        String filename = String.format("house_%s.csv", house.getHouseNumber());
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename))) {
            writer.println("Данные о доме");
            writer.println(";");

            writer.println(String.format("Кадастровый номер: %s", house.getHouseNumber()));
            writer.println(String.format("Адрес: %s", house.getAderess()));
            writer.println(String.format("Старший по дому: %s %s %s",
                    house.getOwners().getLastname(),
                    house.getOwners().getFirstname(),
                    house.getOwners().getSurname()));
            writer.println();

            writer.println("Данные о квартирах");
            writer.println("№;Площадь, кв. м;Владельцы");

            int counter = 1;
            for (Flat flat : house.getFlatsList()) {
                String owners = Arrays.stream(flat.getData())
                        .map(p -> String.format("%s %c.%c.",
                                p.getLastname(),
                                p.getFirstname().charAt(0),
                                p.getSurname().charAt(0)))
                        .collect(Collectors.joining(", "));

                writer.println(String.format("%d;%.1f;%s",
                        counter++,
                        flat.getSquare(),
                        owners));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка при создании файла", e);
        }
    }


    public static boolean compareJsonStrings(String json1, String json2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node1 = objectMapper.readTree(json1);
            JsonNode node2 = objectMapper.readTree(json2);
            return node1.equals(node2);
        } catch (Exception e) {
            throw new IllegalArgumentException("Некорректная JSON-строка", e);
        }
    }







}
