package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import static org.mockito.Mockito.mockStatic;

public class Mockito{
    @Test
    void findFilesByExtension_shouldReturnTxtFiles() {
        Path mockFile1 = Paths.get("file1.txt");
        Path mockFile2 = Paths.get("file2.pdf");
        Stream<Path> mockStream = Stream.of(mockFile1, mockFile2);

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.walk(any())).thenReturn(mockStream);
            mockedFiles.when(() -> Files.isRegularFile(mockFile1)).thenReturn(true);
            mockedFiles.when(() -> Files.isRegularFile(mockFile2)).thenReturn(true);

            List<Path> result = IOStreamDemo.findFilesByExtension("/fake/dir", "txt");

            assertEquals(1, result.size());
            assertTrue(result.get(0).toString().endsWith(".txt"));
        }
    }

    @Test
    void findFiles_shouldReturnFilesByRegex() {
        Path mockFile1 = Paths.get("test1.txt");
        Path mockFile2 = Paths.get("data.log");
        Stream<Path> mockStream = Stream.of(mockFile1, mockFile2);

        try (MockedStatic<Files> mockedFiles = mockStatic(Files.class)) {
            mockedFiles.when(() -> Files.walk(any())).thenReturn(mockStream);

            List<String> result = IOStreamDemo.findFiles("/fake/dir", ".*\\.txt");

            assertEquals(1, result.size());
            assertTrue(result.get(0).endsWith("test1.txt"));
        }
    }
}