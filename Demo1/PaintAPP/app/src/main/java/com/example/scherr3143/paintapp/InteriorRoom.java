package com.example.scherr3143.paintapp;

/**
 * Created by scherr3143 on 1/10/2017.
 */
public class InteriorRoom {

    static final int WINDOW_AREA = 16;
    static final int DOOR_AREA = 21;
    static final int SQR_FEET_PER_GALLON = 275;

    private double mLength;
    private double mWidth;
    private double mHeight;

    private int mDoors;
    private int mWindows;

    public void setLength(double mLength) {
        this.mLength = mLength;
    }

    public void setWidth(double mWidth) {
        this.mWidth = mWidth;
    }

    public void setHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public void setDoors(int mDoors) {
        this.mDoors = mDoors;
    }

    public void setWindows(int mWindows) {
        this.mWindows = mWindows;
    }

    public double wallSurface() {
        return 2*mLength*mHeight + 2*mWidth*mHeight + mLength*mWidth;
    }

    public int doorWindowArea() {
        return mDoors*DOOR_AREA + mWindows*WINDOW_AREA;
    }

    public double surfaceArea() {
        return wallSurface() - doorWindowArea();
    }

    public int gallons(){
        return (int) Math.ceil(surfaceArea()) / SQR_FEET_PER_GALLON;
    }
}
