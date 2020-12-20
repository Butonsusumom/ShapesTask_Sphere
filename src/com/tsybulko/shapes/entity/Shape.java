package com.tsybulko.shapes.entity;

public abstract class Shape {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
