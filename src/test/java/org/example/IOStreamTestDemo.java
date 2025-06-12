package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.Assert.*;
import static org.example.IOStreamDemo.findFiles;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        int[] originalArray = {1,2,3,4,5,6,7,8,9,111110};
        IOStreamDemo.writeInArrayChar(originalArray);

        int[]  readArray = IOStreamDemo.readAtArrayChar(originalArray.length);
        System.out.println(Arrays.toString(readArray));
        //assertArrayEquals(originalArray,readArray);
    }

    @Test
    public void readArraysWithFixStart(){
        int[] originalArray = {1111,2,3,4,5,6,7,8,9,10};
        IOStreamDemo.writeInArrayBin(originalArray);

        int[] readArray = IOStreamDemo.readAtArrayWithPosition(originalArray.length, 6);
        System.out.println(Arrays.toString(readArray));
    }
    @Test
    public void checkExstensions(){
        List<Path> answer = new ArrayList<>();
        answer = IOStreamDemo.findFilesByExtension("C:\\Users\\ozzzzkal\\IdeaProjects\\lab_7\\extensionTest","txt");
        System.out.println(answer);
    }

    @Test
    void testFindFilesWithRegex(@TempDir Path tempDir) throws IOException {
        Path file1 = tempDir.resolve("test1.txt");
        Files.createFile(file1);

        Path subDir = tempDir.resolve("subdir");
        Files.createDirectory(subDir);
        Path file2 = subDir.resolve("test2.log");
        Files.createFile(file2);

        Path file3 = subDir.resolve("data.csv");
        Files.createFile(file3);


        List<String> txtFiles = findFiles(tempDir.toString(), ".*\\.txt");
        assertEquals(1, txtFiles.size());
        assertTrue(txtFiles.get(0).endsWith("test1.txt"));


        List<String> testFiles = findFiles(tempDir.toString(), ".*test.*");
        assertEquals(2, testFiles.size());
    }


    @Test
    void testNonExistingDirectory() {
        assertThrows(RuntimeException.class,
                () -> findFiles("/non/existing/path", ".*"));
    }



    @Test
    void testCompareJsonStringsEqual() {
        String json1 = "{\"name\":\"John\",\"age\":30}";
        String json2 = "{\"age\":30,\"name\":\"John\"}";
        assertTrue(IOStreamDemo.compareJsonStrings(json1, json2));
    }

    @Test
    void testCompareJsonStringsNotEqual() {
        String json1 = "{\"name\":\"John\",\"age\":30}";
        String json2 = "{\"name\":\"Jane\",\"age\":30}";
        assertFalse(IOStreamDemo.compareJsonStrings(json1, json2));
    }

    @Test
    void testCompareJsonStringsInvalidJson() {
        String json1 = "{\"name\":\"John\"";
        String json2 = "{\"name\":\"John\"}";
        assertThrows(IllegalArgumentException.class, () -> IOStreamDemo.compareJsonStrings(json1, json2));
    }

    @Test
    void findFilesByExtension_shouldFindTxtFiles() {
        Path mockFile1 = mock(Path.class);
        when(mockFile1.toString()).thenReturn("test.txt");
        when(mockFile1.getFileName()).thenReturn(Paths.get("test.txt"));

        Stream<Path> mockStream = Stream.of(mockFile1);
        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.walk(any())).thenReturn(mockStream);

            List<Path> result = IOStreamDemo.findFilesByExtension("/fake/path", "txt");

            assertEquals(1, result.size());
            assertTrue(result.get(0).toString().endsWith(".txt"));
        }
    }

    @Test
    void testFindFilesWithMockito() throws IOException {
        Path mockPath1 = FileSystems.getDefault().getPath("test1.txt");
        Path mockPath2 = FileSystems.getDefault().getPath("data.log");
        Stream<Path> mockStream = Stream.of(mockPath1, mockPath2);

        try (MockedStatic<Files> mocked = Mockito.mockStatic(Files.class)) {
            mocked.when(() -> Files.walk(any(Path.class))).thenReturn(mockStream);

            List<String> result = IOStreamDemo.findFiles("mockDirectory", ".*\\.txt");

            assertEquals(1, result.size());
            assertEquals(mockPath1.toAbsolutePath().toString(), result.get(0));

            mocked.verify(() -> Files.walk(any(Path.class)), times(1));
        }
    }


}


