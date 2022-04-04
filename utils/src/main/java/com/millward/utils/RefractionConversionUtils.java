package com.millward.utils;

import com.millward.models.entities.RefractionMeasurement;
import com.millward.utils.exceptions.InvalidUserInputRefractionMeasurementException;

import java.math.BigDecimal;

public class RefractionConversionUtils {

    // Delimiter constant so it can easily be updated
    public static final String REFRACTION_NOTATION_SEPARATOR = " ";

    // Users may want refraction measurements to be converted between positive and negative notations
    public static RefractionMeasurement convertRefraction(RefractionMeasurement refractionMeasurement) {
        RefractionMeasurement convertedRefractionMeasurement = new RefractionMeasurement();
        convertedRefractionMeasurement.setSpherePower(refractionMeasurement.getSpherePower().add(refractionMeasurement.getCylinderPower()));
        convertedRefractionMeasurement.setCylinderPower(refractionMeasurement.getCylinderPower().negate());
        convertedRefractionMeasurement.setXAxis(normalizeAxis(refractionMeasurement.getXAxis() + 90));
        return convertedRefractionMeasurement;
    }

    // An override to streamline raw user being converted
    public static RefractionMeasurement convertRefraction(String rawUserInput) throws InvalidUserInputRefractionMeasurementException {
        RefractionMeasurement refractionMeasurement = parseUserInputToMeasurement(rawUserInput);
        return convertRefraction(refractionMeasurement);
    }

    //  Convert input text to a refraction measurement object for easier data manipulation
    public static RefractionMeasurement parseUserInputToMeasurement(String rawUserInput) throws InvalidUserInputRefractionMeasurementException {
        String[] measurementVars = rawUserInput.split(REFRACTION_NOTATION_SEPARATOR);
        RefractionMeasurement refractionMeasurement;
        if (measurementVars.length != 3) {
            throw new InvalidUserInputRefractionMeasurementException(rawUserInput, "Wrong number of values, should be three (3) seperated by a space.");
        }
        try {
            BigDecimal spherePower = new BigDecimal(measurementVars[0]);
            BigDecimal cylinderPower = new BigDecimal(measurementVars[1]);
            measurementVars[2] = measurementVars[2].replaceAll("(?i)x", "");
            int xAxis = Integer.parseInt(measurementVars[2]);
            refractionMeasurement = new RefractionMeasurement(spherePower, cylinderPower, normalizeAxis(xAxis));
        } catch (NumberFormatException e) {
            throw new InvalidUserInputRefractionMeasurementException(rawUserInput, e.getMessage());
        }
        return refractionMeasurement;
    }

    // Axis values could be outside 0-180 but are valid. Normalize them based on 180 degree rotations
    public static int normalizeAxis(int xAxis) {
        while (xAxis > 180) {
            xAxis = xAxis - 180;
        }
        while (xAxis < 0) {
            xAxis = xAxis + 180;
        }
        return xAxis;
    }
}
