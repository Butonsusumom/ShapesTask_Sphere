package com.tsybulko.shapes.factory;

import com.tsybulko.shapes.entity.Shape;
import com.tsybulko.shapes.exception.SphereFactoryCreateException;

import java.util.List;

public interface ShapeFactory {
    Shape create(List<Double> values) throws SphereFactoryCreateException;
}
