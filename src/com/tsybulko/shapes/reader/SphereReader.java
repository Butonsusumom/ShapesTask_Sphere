package com.tsybulko.shapes.reader;

import com.tsybulko.shapes.exception.DaoException;

import java.io.IOException;
import java.util.List;

public interface SphereReader {
    List<String> getStrings(String fileName) throws IOException, DaoException;
}
