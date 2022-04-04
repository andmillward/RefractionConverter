package com.millward.models.entities;

public class RefractionMeasurement {
    private double spherePower;
    private double cylinderPower;
    private int xAxis;

    public RefractionMeasurement(double spherePower, double cylinderPower, int xAxis) {
        this.setSpherePower(spherePower);
        this.setCylinderPower(cylinderPower);
        this.setXAxis(xAxis);
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

    public int getXAxis() {
        return xAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    // Format to proper notation before printing
    public String toString() {
        return String.format(
                "Sphere Power: %.2f" +
                "\nCylinder Power: %.2f" +
                "\nAxis: x%03d" +
                // Re-print values on one line for easy copying for the user
                "\n%1$.2f %2$.2f x%3$03d", getSpherePower(), getCylinderPower(), getXAxis());
    }

    public boolean isPlusCylinder() {
        return getCylinderPower() >= 0;
    }
}
