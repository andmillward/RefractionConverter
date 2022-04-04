package com.millward.models.entities;

import java.math.BigDecimal;

public class RefractionMeasurement {
    private BigDecimal spherePower;
    private BigDecimal cylinderPower;
    private int xAxis;

    public RefractionMeasurement(BigDecimal spherePower, BigDecimal cylinderPower, int xAxis) {
        this.setSpherePower(spherePower);
        this.setCylinderPower(cylinderPower);
        this.setXAxis(xAxis);
    }

    public BigDecimal getSpherePower() {
        return spherePower;
    }

    public void setSpherePower(BigDecimal spherePower) {
        this.spherePower = spherePower;
    }

    public BigDecimal getCylinderPower() {
        return cylinderPower;
    }

    public void setCylinderPower(BigDecimal cylinderPower) {
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
        return getCylinderPower().compareTo(BigDecimal.ZERO) >= 0;
    }
}
