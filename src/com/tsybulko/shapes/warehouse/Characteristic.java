package com.tsybulko.shapes.warehouse;

public class Characteristic {
    private double square;
    private double volume;
    private boolean isTouchPlane;
    private double volumeRatio;

    public Characteristic(){}

    public double getSquare() {
        return square;
    }

    public double getVolume() {
        return volume;
    }

    public boolean isTouchPlane() {
        return isTouchPlane;
    }

    public double getVolumeRatio() {
        return volumeRatio;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setTouchPlane(boolean touchPlane) {
        isTouchPlane = touchPlane;
    }

    public void setVolumeRatio(double volumeRatio) {
        this.volumeRatio = volumeRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()){
            return false;
        }

        Characteristic characteristic = (Characteristic) o;
        if (Double.compare(characteristic.square, square) != 0) {
            return false;
        }
        if (Double.compare(characteristic.volume, volume) != 0) {
            return false;
        }
        if (Double.compare(characteristic.volumeRatio, volumeRatio) == 0){
            return false;
        }
        return (isTouchPlane == characteristic.isTouchPlane);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(square);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isTouchPlane ? 1 : 0);
        temp = Double.doubleToLongBits(volumeRatio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getName());
        stringBuilder.append("square=");
        stringBuilder.append(square);
        stringBuilder.append(", volume=");
        stringBuilder.append(volume);
        stringBuilder.append(", isTouchPlane=");
        stringBuilder.append(isTouchPlane);
        stringBuilder.append(", volumeRatio=");
        stringBuilder.append(volumeRatio);
        return stringBuilder.toString();
    }
}
