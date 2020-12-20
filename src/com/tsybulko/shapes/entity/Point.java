package com.tsybulko.shapes.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Point {
    private double coordinateX;
    private double coordinateY;
    private double coordinateZ;
    private static Logger logger = LogManager.getLogger();

    public Point(){
        logger.debug("default Point was created");
    }

    public Point(double coordinateX, double coordinateY, double coordinateZ){
        logger.debug("parameters: " + coordinateX + ", " + coordinateY + ", " + coordinateZ);
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
        logger.info("Point with define coordinates was created");
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public double getCoordinateZ() {
        return coordinateZ;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public void setCoordinateZ(double coordinateZ) {
        this.coordinateZ = coordinateZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Point point = (Point) o;
        if (Double.compare(point.coordinateX, coordinateX) != 0) {
            return false;
        }
        if (Double.compare(point.coordinateY, coordinateY) != 0){
            return false;
        }
        return Double.compare(point.coordinateZ, coordinateZ) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(coordinateX);
        int result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coordinateY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coordinateZ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getName());
        stringBuilder.append(": coordinateX=");
        stringBuilder.append(coordinateX);
        stringBuilder.append(", coordinateY=");
        stringBuilder.append(coordinateY);
        stringBuilder.append(", coordinateZ=");
        stringBuilder.append(coordinateZ);
        return stringBuilder.toString();
    }
}
