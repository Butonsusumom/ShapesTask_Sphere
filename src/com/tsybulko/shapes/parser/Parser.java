package com.tsybulko.shapes.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final static Pattern SPHERE_STRING = Pattern.compile("((-?\\d+?\\.?\\d{0,2}\\s){3})(\\d+?\\.?\\d{0,2})");
    private final static String DELIMITER = " ";
    private static Logger logger = LogManager.getLogger();

    public List<List<Double>> parseValue(List<String> listString){
        logger.debug("parameter: String for parsing: " + listString);
        List<List<Double>> sphereValuesList = new ArrayList<List<Double>>();
        Matcher matcher;
        for (String s : listString){
            logger.info("inside for, String: " + s);
            matcher = SPHERE_STRING.matcher(s);
            if(matcher.matches()){
                logger.info("String was matched successful");
                List<Double> values = collectValues(s);
                sphereValuesList.add(values);
            } else {
                logger.debug("Matching failed");
            }
        }
        logger.info("result List parameters for Sphere creation: " + sphereValuesList);
        return sphereValuesList;
    }

    private List<Double> collectValues(String string){
        logger.debug("parameter: String with values: " + string);
        List<Double> valuesList = new ArrayList<>();
        String[] valuesArr = string.split(DELIMITER);
        for (String s: valuesArr){
            valuesList.add(Double.parseDouble(s));
        }
        logger.info("result List of values for sphere: " + valuesList);
        return valuesList;
    }
}
