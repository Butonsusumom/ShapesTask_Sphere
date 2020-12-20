package com.tsybulko.shapes.warehouse;

import com.tsybulko.shapes.action.SphereAction;
import com.tsybulko.shapes.entity.Sphere;
import com.tsybulko.shapes.util.observer.Observer;
import com.tsybulko.shapes.util.observer.SphereEvent;

public class SphereObserver implements Observer {
    private static final SphereObserver instance = new SphereObserver();

    private SphereObserver(){}

    public static SphereObserver getInstance(){
        return instance;
    }

    @Override
    public void update(SphereEvent event){
        Sphere sphere = event.getSource();
        SphereAction action = new SphereAction();
        double square = action.findSurfaceSquare(sphere);
        double volume = action.findVolume(sphere);
        boolean isTouch = action.isTouchCoordinatePlane(sphere);
        double volumeRatio = action.findVolumeRatio(sphere);

        Warehouse warehouse = Warehouse.getInstance();
        Characteristic characteristic;
        if (warehouse.contains(sphere.getId())){
            characteristic = warehouse.getCharacteristic(sphere.getId());
        } else {
            characteristic = new Characteristic();
        }
        characteristic.setSquare(square);
        characteristic.setVolume(volume);
        characteristic.setTouchPlane(isTouch);
        characteristic.setVolumeRatio(volumeRatio);


        warehouse.putCharacteristic(sphere.getId(), characteristic);
    }
}
