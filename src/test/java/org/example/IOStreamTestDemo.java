package org.example;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IOStreamTestDemo {

    @Test
    public void testWriteAndReadFullArray() {
        int[] originalArray = {1, 2, 3, 4, 5};
        IOStreamDemo.writeInArrayBin(originalArray);

        int[] readArray = IOStreamDemo.readAtArrayBin(originalArray.length);
        System.out.println(readArray);
        assertArrayEquals(originalArray, readArray);
    }

    @Test
    public void writeAndReadArrayChars(){
        int[] originalArray = {1,2,3,4,5,6,7,8,9,10};
        IOStreamDemo.writeInArrayChar(originalArray);

        int[]  readArray = IOStreamDemo.readAtArrayChar(originalArray.length);
        System.out.println(Arrays.toString(readArray));
        //assertArrayEquals(originalArray,readArray);
    }

    @Test
    public void readArraysWithFixStart(){
        int[] originalArray = {1,2,3,4,5,6,7,8,9,10};
        IOStreamDemo.writeInArrayChar(originalArray);

        int[]  readArray = IOStreamDemo.readAtArrayWithPosition(originalArray.length, 4);
        System.out.println(Arrays.toString(readArray));
    }
    @Test
    public void checkExstensions(){
        List<Path> answer = new ArrayList<>();
        answer = IOStreamDemo.findFilesByExtension("C:\\Users\\ozzzzkal\\IdeaProjects\\lab_7\\extensionTest","txt");
        System.out.println(answer);
    }


}