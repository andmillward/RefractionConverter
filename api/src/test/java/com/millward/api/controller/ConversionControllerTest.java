package com.millward.api.controller;

import com.millward.models.entities.RefractionMeasurement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversionControllerTest {

    @Test
    void getConvertedMeasurements() {
        ConversionController controller = new ConversionController();
        
        List<RefractionMeasurement> refractionMeasurementsTest = new ArrayList<>();
        refractionMeasurementsTest.add(new RefractionMeasurement(new BigDecimal("1.50"), new BigDecimal("-1.25"), 80));
        refractionMeasurementsTest.add(new RefractionMeasurement(new BigDecimal("4.55"), new BigDecimal("16.54"), 1234));

        List<RefractionMeasurement> refractionMeasurementsResult = new ArrayList<>();
        refractionMeasurementsResult = controller.getConvertedMeasurements(refractionMeasurementsTest);

        if (refractionMeasurementsResult.size() != 2) {
            fail("Wrong number of converted objects. Should be 2 is actually " + refractionMeasurementsResult.size());
        }

        assertEquals(new BigDecimal("0.25"), refractionMeasurementsResult.get(0).getSpherePower());
        assertEquals(new BigDecimal("1.25"), refractionMeasurementsResult.get(0).getCylinderPower());
        assertEquals(170, refractionMeasurementsResult.get(0).getXAxis());

        assertEquals(new BigDecimal("21.09"), refractionMeasurementsResult.get(1).getSpherePower());
        assertEquals(new BigDecimal("-16.54"), refractionMeasurementsResult.get(1).getCylinderPower());
        assertEquals(64, refractionMeasurementsResult.get(1).getXAxis());
    }
}