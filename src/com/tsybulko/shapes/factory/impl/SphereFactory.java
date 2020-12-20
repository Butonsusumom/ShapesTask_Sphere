package com.tsybulko.shapes.factory.impl;

import com.tsybulko.shapes.entity.Point;
import com.tsybulko.shapes.entity.Shape;
import com.tsybulko.shapes.entity.Sphere;
import com.tsybulko.shapes.exception.IncorrectDataException;
import com.tsybulko.shapes.exception.SphereFactoryCreateException;
import com.tsybulko.shapes.factory.ShapeFactory;
import com.tsybulko.shapes.util.generator.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SphereFactory implements ShapeFactory {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Shape create(List<Double> values) throws SphereFactoryCreateException {
        logger.debug("parameters: " + values);
        double centerX = values.get(0);
        double centerY = values.get(1);
        double centerZ = values.get(2);
        Point centerPoint = new Point(centerX, centerY, centerZ);
        double radius = values.get(3);
        Shape sphere;
        try {
            sphere = new Sphere(centerPoint, radius);
        } catch (IncorrectDataException e) {
            logger.error("IncorrectDataException was thrown: " + e.getMessage());
            throw new SphereFactoryCreateException(e);
        }
        IdGenerator generator = new IdGenerator();
        long id = generator.generateId();
        sphere.setId(id);
        logger.info("Sphere was created, sphere.id=" + sphere.getId());
        return sphere;
    }
}
