package com.tsybulko.shapes.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Warehouse instance = new Warehouse();
    private Map<Long, Characteristic> sphereCharacteristics = new HashMap<>();

    private Warehouse(){}

    public static Warehouse getInstance() {
        return instance;
    }

    public boolean putCharacteristic(long id, Characteristic characteristic){
        sphereCharacteristics.put(id, characteristic);
        return true;
    }

    public boolean contains(long id){
        return sphereCharacteristics.containsKey(id);
    }

    public Characteristic getCharacteristic (long id){
        return sphereCharacteristics.get(id);
    }

}
