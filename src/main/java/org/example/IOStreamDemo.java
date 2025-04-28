package org.example;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOStreamDemo {
    public static void writeInArrayBin(int[] items){
        try(final DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get("file.txt")))) {
            for (Integer num : items){
                dos.writeInt(num);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] readAtArrayBin(int n){
        int[] result = new int[n];
        try(DataInputStream dos = new DataInputStream(Files.newInputStream(Paths.get("file.txt")))){
            for (int i = 0; i<n; i++){
                result[i] = dos.readInt();
            }
            return result;
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void writeInArrayChar(int[] items){
        try(FileWriter dos = new FileWriter("file3.txt")) {
            for(Integer item : items){
                dos.write(String.valueOf(item));
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
            for (int i = 0; i< numbers.length; i++){
                result[i] = Integer.parseInt(numbers[i]);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static int[] readAtArrayWithPosition(int n, int start){
        String [] numbers;
        int[] result = new int[n-start];
        try (RandomAccessFile raf = new RandomAccessFile("file3.txt","r")){
            raf.seek(start*2);
            numbers = raf.readLine().split(" ");


            for (int i = 0; i<numbers.length;i++){
                result[i] = Integer.parseInt(numbers[i]);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public static List<Path> findFilesByExtension(String directory,String extension){
        try(Stream<Path> path = Files.walk(Paths.get(directory))){//рекурсивно обходим файлы дирректории
            return path
                    .filter(Files::isRegularFile)//проверям что пришли к обычному файлу а не к дирректории
                    .filter(file -> file.toString().endsWith("." + extension))//
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }





}
