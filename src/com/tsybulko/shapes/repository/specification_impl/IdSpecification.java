package com.tsybulko.shapes.repository.specification_impl;

import com.tsybulko.shapes.entity.Sphere;
import com.tsybulko.shapes.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdSpecification implements Specification {
    private long id;
    private static Logger logger = LogManager.getLogger();

    public IdSpecification(long id){
        this.id = id;
        logger.info("IdSpecification created, id = " + this.id);
    }

    @Override
    public boolean specify(Sphere sphere){
        logger.debug("parameter: Sphere" + sphere);
        boolean result = id == sphere.getId();
        logger.debug("id == sphere.getId() : " + result);
        return result;
    }
}
