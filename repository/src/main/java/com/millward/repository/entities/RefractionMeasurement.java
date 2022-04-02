package com.millward.repository.entities;

public class RefractionMeasurement {
    private double spherePower;
    private double cylinderPower;
    private int xAxis;



    public RefractionMeasurement() {
        super();
    }

    public RefractionMeasurement(double spherePower, double cylinderPower, int xAxis) {
        this.setSpherePower(spherePower);
        this.setCylinderPower(cylinderPower);
        this.setxAxis(xAxis);
    }

    public double getSpherePower() {
        return spherePower;
    }

    public void setSpherePower(double spherePower) {
        this.spherePower = spherePower;
    }

    public double getCylinderPower() {
        return cylinderPower;
    }

    public void setCylinderPower(double cylinderPower) {
        this.cylinderPower = cylinderPower;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        if (xAxis > 180) {
            xAxis = xAxis - 180;
        } else if (xAxis < 0) {
            xAxis = xAxis + 180;
        }
        this.xAxis = xAxis;
    }

    public String toString() {
        return String.format("Sphere Power: %f \nCylinder Power: %f\nAxis: %d", getSpherePower(), getCylinderPower(), getxAxis());
    }

    public boolean isPlusCylinder() {
        return xAxis >= 0;
    }
}
