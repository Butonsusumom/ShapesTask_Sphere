package com.tsybulko.shapes.validation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {

    public boolean isFileExist(String fileName){
        Path filePath = Paths.get(fileName);
        return Files.exists(filePath);
    }

    public boolean isEmptyFile(Path filePath){
        boolean result = true;
        long size = 0L;
        try{
            size = Files.size(filePath);
        } catch (IOException e){
            return result;
        }
        if(size > 0){
            result = false;
        }
        return result;
    }
}
