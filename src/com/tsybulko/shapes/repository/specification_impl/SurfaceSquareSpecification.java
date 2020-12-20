package com.tsybulko.shapes.repository.specification_impl;

import com.tsybulko.shapes.action.SphereAction;
import com.tsybulko.shapes.entity.Sphere;
import com.tsybulko.shapes.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SurfaceSquareSpecification implements Specification {
    private double minSquare;
    private double maxSquare;
    private static Logger logger = LogManager.getLogger();

    public SurfaceSquareSpecification(double minSquare, double maxSquare){
        logger.debug("parameters: minimal square: " + minSquare + ", maximum square: " + maxSquare);
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
        logger.info("SquareSpecification created, minSquare = " + this.minSquare + ", maxSquare = " + this.maxSquare);
    }

    @Override
    public boolean specify(Sphere sphere){
        logger.debug("parameter: Sphere" + sphere);
        SphereAction action = new SphereAction();
        double square = action.findSurfaceSquare(sphere);
        logger.info("Square of Sphere = " + square);
        boolean minBorder = Double.compare(square, minSquare) >= 0;
        logger.info("Sphere square greater or equal minSquare: " + minBorder);
        boolean maxBorder = Double.compare(square, maxSquare) <= 0;
        logger.info("Sphere square less or equal maxSquare: " + maxBorder);
        boolean result = minBorder && maxBorder;
        logger.debug("result: square meets conditions: " + result);
        return result;
    }
}
