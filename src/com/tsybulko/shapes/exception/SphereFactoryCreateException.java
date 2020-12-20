package com.tsybulko.shapes.exception;

public class SphereFactoryCreateException extends Exception {
    static final long serialVersionUID = 1L;

    public SphereFactoryCreateException(){
        super();
    }

    public SphereFactoryCreateException(String message){
        super(message);
    }

    public SphereFactoryCreateException(Exception e){
        super(e);
    }

    public SphereFactoryCreateException(String message, Exception e){
        super(message, e);
    }
}
