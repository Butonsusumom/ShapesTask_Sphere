package com.tsybulko.shapes.repository;

import com.tsybulko.shapes.entity.Point;
import com.tsybulko.shapes.entity.Sphere;
import com.tsybulko.shapes.exception.IncorrectDataException;
import com.tsybulko.shapes.repository.specification_impl.IdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SphereRepository {
    private static final SphereRepository instance = new SphereRepository();
    private final List<Sphere> sphereList = new ArrayList<>();
    private static Logger logger = LogManager.getLogger();

    private SphereRepository(){}

    public static SphereRepository getInstance() {
        return instance;
    }

    public List<Sphere> getSphereList(){
        return sphereList;
    }

    public boolean addSphere(Sphere sphere){
        logger.debug("parameter: Sphere for adding: " + sphere);
        return sphereList.add(sphere);
    }

    public boolean changeSphere(long id, double newRadius){
        logger.info("called method to change radius of sphere");
        logger.debug("parameters: id = " + id + ", radius = " + newRadius);
        Specification idSpecification = new IdSpecification(id);
        List<Sphere> sphereList = query(idSpecification);
        if (sphereList.isEmpty()){
            logger.debug("No Sphere with specified Id");
            return false;
        }
        Sphere sphere = sphereList.get(0);
        try{
            sphere.setRadius(newRadius);
            logger.info("Radius was changed successfully, new radius = " + sphere.getRadius());
        } catch (IncorrectDataException e){
            logger.error("IncorrectDataException was thrown: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean changeSphere(long id, Point newCenterPoint){
        logger.info("called method to change center point of sphere");
        logger.debug("parameters: id = " + id + ", center point = " + newCenterPoint);
        Specification idSpecification = new IdSpecification(id);
        List<Sphere> sphereList = query(idSpecification);
        if (sphereList.isEmpty()){
            logger.debug("No Sphere with specified Id");
            return false;
        }
        Sphere sphere = sphereList.get(0);
        try{
            sphere.setCenterPoint(newCenterPoint);
            logger.info("Center point was changed successfully, new center point = " + sphere.getCenterPoint());
        } catch (IncorrectDataException e){
            logger.error("IncorrectDataException was thrown: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeSphere(Sphere sphere){
        logger.debug("parameter: Sphere for removing: " + sphere);
        boolean result = sphereList.remove(sphere);
        logger.debug("result of removing Sphere: " + result);
        return result;
    }

    public List<Sphere> sort(Comparator comparator){
        logger.debug("parameter: Comparator: " + comparator);
        List<Sphere> sortedList = new ArrayList<>(sphereList);
        sortedList.sort(comparator);
        logger.debug("sorted List: " + sortedList);
        return sortedList;
    }

    public List<Sphere> query(com.tsybulko.shapes.repository.Specification specification){
        logger.debug("parameter: Specification: " + specification.getClass().getName());
        List<Sphere> resultList = new ArrayList<>();
        for (Sphere current : sphereList){
            logger.info("inside for: Sphere: " + current);
            if (specification.specify(current)){
                resultList.add(current);
                logger.info("sphere added to result List");
            }
        }
        logger.debug("result List of query(): " + resultList);
        return resultList;
    }
}
