package com.millward.models.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefractionMeasurementTest {

    @Test
    void testToString() {
        String expected = "Sphere Power: 1.23" +
                "\nCylinder Power: -1.40" +
                "\nAxis: x043" +
                "\n1.23 -1.40 x043";
        RefractionMeasurement testMeasurement = new RefractionMeasurement(1.23, -1.4, 43);
        assertEquals(expected, testMeasurement.toString());
    }

    @Test
    void isPlusCylinder() {
        RefractionMeasurement refractionMeasurement = new RefractionMeasurement(1.56, -23, 34);
        assertEquals(false, refractionMeasurement.isPlusCylinder());

        refractionMeasurement.setCylinderPower(43.34);
        assertEquals(true, refractionMeasurement.isPlusCylinder());

        // We're treating zero as positive until there's a good reason not to
        refractionMeasurement.setCylinderPower(0);
        assertEquals(true, refractionMeasurement.isPlusCylinder());
    }
}