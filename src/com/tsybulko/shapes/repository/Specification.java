package com.tsybulko.shapes.repository;

import com.tsybulko.shapes.entity.Sphere;

public interface Specification <T extends Sphere> {
    boolean specify(T t);
}
