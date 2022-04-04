package com.millward.utils;

import com.millward.models.entities.RefractionMeasurement;
import com.millward.utils.exceptions.InvalidUserInputRefractionMeasurementException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RefractionConversionUtilsTest {

    @Test
    void convertRefraction() {
        try {
            // Test if raw user input override works
            RefractionMeasurement testRefraction = RefractionConversionUtils.convertRefraction("-3.75 +2.00 x154");
            assertFalse(testRefraction.isPlusCylinder());
            assertEquals(new BigDecimal("-1.75"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("-2.00"), testRefraction.getCylinderPower());
            assertEquals(64, testRefraction.getXAxis());

            // Test if the same values that were parsed produce the same results
            testRefraction = new RefractionMeasurement(new BigDecimal("-3.75"), new BigDecimal("2"), 154);
            RefractionConversionUtils.convertRefraction(testRefraction);
            assertEquals(new BigDecimal("-1.75"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("-2"), testRefraction.getCylinderPower());
            assertEquals(64, testRefraction.getXAxis());

            testRefraction = new RefractionMeasurement(new BigDecimal("50000.12312312"), new BigDecimal("434344.2342344563"), -951568);
            RefractionConversionUtils.convertRefraction(testRefraction);
            assertEquals(new BigDecimal("484344.3573575763"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("-434344.2342344563"), testRefraction.getCylinderPower());
            assertEquals(2, testRefraction.getXAxis());

            testRefraction = new RefractionMeasurement(new BigDecimal("-1"), new BigDecimal("1"), 0);
            RefractionConversionUtils.convertRefraction(testRefraction);
            assertEquals(new BigDecimal("0"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("-1"), testRefraction.getCylinderPower());
            assertEquals(90, testRefraction.getXAxis());

        } catch (InvalidUserInputRefractionMeasurementException e) {
            fail("Invalid user input was thrown on correct input: " + e.getMessage());
        }

        // Test for legitimate failure
        try {
            RefractionMeasurement testRefraction = RefractionConversionUtils.convertRefraction("1.25 asdfsadf xsdafs");
            fail("Invalid user input exception should be thrown");
        } catch (InvalidUserInputRefractionMeasurementException e) {
            // Task failed successfully
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void parseUserInputToMeasurement() {
        try {
            RefractionMeasurement testRefraction = RefractionConversionUtils.parseUserInputToMeasurement("-3.75 +2.00 x154");
            assertEquals(new BigDecimal("-3.75"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("2.00"), testRefraction.getCylinderPower());
            assertEquals(154, testRefraction.getXAxis());

            testRefraction = RefractionConversionUtils.parseUserInputToMeasurement("-32423432.45645 +6786785 x-123134");
            assertEquals(new BigDecimal("-32423432.45645"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("6786785"), testRefraction.getCylinderPower());
            assertEquals(166, testRefraction.getXAxis());

            testRefraction = RefractionConversionUtils.parseUserInputToMeasurement("123 -32.6 x11");
            assertEquals(new BigDecimal("123"), testRefraction.getSpherePower());
            assertEquals(new BigDecimal("-32.6"), testRefraction.getCylinderPower());
            assertEquals(11, testRefraction.getXAxis());

        } catch (InvalidUserInputRefractionMeasurementException e) {
            fail("Invalid user input was thrown on correct input: " + e.getMessage());
        }

        // Test for legitimate failure
        try {
            RefractionMeasurement testRefraction = RefractionConversionUtils.convertRefraction("1.25 1.25 xsdafs");
            fail("Invalid user input exception should be thrown");
        } catch (InvalidUserInputRefractionMeasurementException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void normalizeAxis() {
        int normalizedAxis = RefractionConversionUtils.normalizeAxis(802330984);
        assertEquals(64, normalizedAxis);

        normalizedAxis = RefractionConversionUtils.normalizeAxis(0);
        assertEquals(0, normalizedAxis);

        normalizedAxis = RefractionConversionUtils.normalizeAxis(181);
        assertEquals(1, normalizedAxis);

        normalizedAxis = RefractionConversionUtils.normalizeAxis(-59);
        assertEquals(121, normalizedAxis);
    }
}