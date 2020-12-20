package com.tsybulko.shapes.exception;

public class EmptySourceException extends DaoException {
    static final long serialVersionUID = 1L;

    public EmptySourceException(){
        super();
    }

    public EmptySourceException(String message){
        super(message);
    }

    public EmptySourceException(Exception e){
        super(e);
    }

    public EmptySourceException(String message, Exception e){
        super(message, e);
    }
}
