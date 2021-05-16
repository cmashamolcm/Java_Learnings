package com.java11.features;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWithString {
    public static void main(String[] args) throws IOException {
        String data = Files.readString(Path.of("/Users/ganeshkv/AshamolCM/workspace/Java_Learnings/Java11Features/src/main/java/com/java11/features/StringMethods.java"), Charset.defaultCharset());
        System.out.println(data);
        Files.writeString(Path.of("/Users/ganeshkv/AshamolCM/workspace/Java_Learnings/Java11Features/src/main/java/com/java11/features/out.txt"), "Abc to save to file");
    }
}
