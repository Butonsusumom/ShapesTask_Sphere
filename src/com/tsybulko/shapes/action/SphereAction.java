package com.tsybulko.shapes.action;

import com.tsybulko.shapes.entity.Point;
import com.tsybulko.shapes.entity.Sphere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SphereAction {
    private static Logger logger = LogManager.getLogger();

    public double findSurfaceSquare(Sphere sphere){
        logger.debug("parameter: Sphere: " + sphere);
        double radius = sphere.getRadius();
        double square = 4.0 * Math.PI * Math.pow(radius, 2.0);
        logger.debug("square = " + square);
        return square;
    }

    public double findVolume(Sphere sphere){
        logger.debug("parameter: Sphere: " + sphere);
        double radius = sphere.getRadius();
        double volume = (4.0/3.0) * Math.PI * Math.pow(radius, 3.0);
        logger.debug("volume = " + volume);
        return volume;
    }

    public boolean isTouchCoordinatePlane(Sphere sphere){
        logger.debug("parameter: Sphere: " + sphere);
        double radius = sphere.getRadius();
        Point centerPoint = sphere.getCenterPoint();
        if (Double.compare(radius, Math.abs(centerPoint.getCoordinateX())) == 0){
            logger.debug("Sphere touch YZ plane");
            return true;
        } else if(Double.compare(radius, Math.abs(centerPoint.getCoordinateY())) == 0){
            logger.debug("Sphere touch XZ plane");
            return true;
        }
        boolean result = Double.compare(radius, Math.abs(centerPoint.getCoordinateZ())) == 0;
        if (result){
            logger.debug("Sphere touch XY plane");
        } else {
            logger.debug("Sphere doesn't touch any plane");
        }
        return result;
    }

    public double findVolumeRatio(Sphere sphere){
        logger.debug("parameter: Sphere: " + sphere);
        double fullRadius = sphere.getRadius();
        double distance = findMinDistanceToPlane(sphere);
        double ratio = 0.0;
        if (Double.compare(fullRadius, distance) > 0) {
            logger.info("distance to plane less radius of Sphere");
            double h = fullRadius - distance;
            logger.info("part on the other side of plane = " + h);
            double radius = Math.sqrt(Math.pow(fullRadius, 2) - Math.pow(fullRadius - h, 2));
            logger.info("radius of spherical cap = " + radius);
            double partOne = Math.PI * Math.pow(h, 2.0) * (radius - (1.0 / 3.0) * h);
            logger.info("volume of spherical cap = " + partOne);
            double partTwo = findVolume(sphere) - partOne;
            logger.info("volume of other part = " + partTwo);
            ratio = partTwo / partOne;
        } else {
            logger.info("Sphere doesn't cross any plane");
        }
        logger.debug("ratio = " + ratio);
        return ratio;
    }

    private double findMinDistanceToPlane(Sphere sphere){
        Point centerPoint = sphere.getCenterPoint();
        logger.debug("point: " + centerPoint);
        double xCoordinate = centerPoint.getCoordinateX();
        double minDistance = Math.abs(xCoordinate);
        logger.info("minDistance == xCoordinate : " + minDistance);

        double yCoordinate = centerPoint.getCoordinateY();
        double distanceByY = Math.abs(yCoordinate);
        if (Double.compare(minDistance, distanceByY) > 0){
            minDistance = distanceByY;
            logger.info("new minDistance == yCoordinate : " + minDistance);
        }
        double zCoordinate = centerPoint.getCoordinateZ();
        double distanceByZ = Math.abs(zCoordinate);
        if (Double.compare(minDistance, distanceByZ) > 0){
            minDistance = distanceByZ;
            logger.info("new minDistance == zCoordinate : " + minDistance);
        }
        logger.debug("result: minDistance = " + minDistance);
        return minDistance;
    }
}
