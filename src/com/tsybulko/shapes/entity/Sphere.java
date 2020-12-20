package com.tsybulko.shapes.entity;

import com.tsybulko.shapes.exception.IncorrectDataException;
import com.tsybulko.shapes.util.observer.Observable;
import com.tsybulko.shapes.util.observer.Observer;
import com.tsybulko.shapes.util.observer.SphereEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends Shape implements Observable {
    private long id;
    private Point centerPoint;
    private double radius;
    private static Logger logger = LogManager.getLogger();
    private List<Observer> observerList = new ArrayList<>();

    public Sphere(){
        centerPoint = new Point();
        radius = 1.0;
        logger.debug("default Sphere was created, center point = " + centerPoint + ", radius = " + radius);
    }

    public Sphere(Point centerPoint, double radius) throws IncorrectDataException{
        if(centerPoint == null || radius <= 0){
            logger.error("centerPoint is null reference or radius less or equal zero");
            throw new IncorrectDataException("invalid data for sphere creation");
        }
        logger.debug("parameters: center point = " + centerPoint + ", radius = " + radius);
        double x = centerPoint.getCoordinateX();
        double y = centerPoint.getCoordinateY();
        double z = centerPoint.getCoordinateZ();
        logger.info("create new Point for Sphere.centerPoint");
        this.centerPoint = new Point(x, y, z);
        this.radius = radius;
        logger.debug("Sphere with define parameters was created, center point = " + centerPoint + ", radius = " + radius);
    }

    public long getId() {
        return id;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return radius;
    }

    public void setId(long id) {
        this.id = id;
        notifyObserver();
    }

    public void setCenterPoint(Point centerPoint) throws IncorrectDataException{
        if(centerPoint == null){
            logger.fatal("Point centerPoint is null");
            throw new IncorrectDataException("null reference to Point object");
        } else {
            double x = centerPoint.getCoordinateX();
            double y = centerPoint.getCoordinateY();
            double z = centerPoint.getCoordinateZ();
            this.centerPoint = new Point(x, y, z);
            logger.info("center point was set, center point: " + this.centerPoint);
            notifyObserver();
        }
    }

    public void setRadius(double radius) throws IncorrectDataException {
        if(radius > 0.0){
            this.radius = radius;
            logger.info("center point was set, center point: " + radius);
            notifyObserver();
        } else {
            logger.fatal("radius less or equal zero");
            throw new IncorrectDataException("radius less or equal zero");
        }
    }

    @Override
    public void attach(Observer observer){
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer){
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver(){
        if (!observerList.isEmpty()){
            for (Observer current: observerList){
                if (current != null){
                    current.update(new SphereEvent(this));
                }
            }
        }
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null){
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Sphere sphere = (Sphere) o;
        if (id != sphere.id){
            return false;
        }
        if (Double.compare(sphere.radius, radius) != 0) {
            return false;
        }
        if (!observerList.equals(sphere.observerList)){
            return false;
        }
        if (centerPoint == null){
            return (sphere.centerPoint == null);
        } else {
            return centerPoint.equals(sphere.centerPoint);
        }
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        int primeNumber = 31;
        result = primeNumber * result + ((centerPoint == null) ? 0 : centerPoint.hashCode());
        long temp = Double.doubleToLongBits(radius);
        result = primeNumber * result + (int) (temp ^ (temp >>> 32));
        result = primeNumber * result + observerList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getName());
        stringBuilder.append("centerPoint=");
        stringBuilder.append(centerPoint);
        stringBuilder.append(", radius=");
        stringBuilder.append(radius);
        return stringBuilder.toString();
    }
}
