package com.tsybulko.shapes.reader.impl;

import com.tsybulko.shapes.exception.EmptySourceException;
import com.tsybulko.shapes.reader.SphereReader;
import com.tsybulko.shapes.exception.DaoException;
import com.tsybulko.shapes.validation.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSphereReader implements SphereReader {
    private static final String DEFAULT_FILENAME = "resource/file.txt";
    private static Logger logger = LogManager.getLogger();

    @Override
    public List<String> getStrings(String fileName) throws DaoException{
        logger.debug("parameter: Sting fileName: " + fileName);
        if (fileName == null){
            logger.fatal("String fileName is null");
            throw new DaoException(" file name is null");
        }
        Path path;
        FileValidator fileValidator = new FileValidator();
        if (!fileValidator.isFileExist(fileName)) {
            logger.info("file wasn't found, try to read default file: " + DEFAULT_FILENAME);
            path = Paths.get(DEFAULT_FILENAME);
        } else {
            logger.info("file was founded");
            path = Paths.get(fileName);
        }

        if (fileValidator.isEmptyFile(path)){
            throw new EmptySourceException("Source file is empty");
        }

        List<String> linesList;
        try{
            logger.info("read all lines was successful");
            linesList = Files.readAllLines(path);
        }catch (IOException e){
            logger.fatal("IOException while readAllLines method");
            throw new DaoException(e);
        }
        return linesList;

//        if (linesList.isEmpty()){
//            throw new EmptySourceException("Source file is empty");
//        } else {
//            return linesList;
//        }
    }
}
