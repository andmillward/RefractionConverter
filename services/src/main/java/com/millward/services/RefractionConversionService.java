package com.millward.services;

import com.millward.repository.entities.RefractionMeasurement;
import com.millward.services.exceptions.InvalidUserInputRefractionMeasurementException;
import org.springframework.stereotype.Service;

@Service
public class RefractionConversionService {
    public RefractionMeasurement convertRefraction(RefractionMeasurement refractionMeasurement) {
        refractionMeasurement.setSpherePower(refractionMeasurement.getSpherePower() + refractionMeasurement.getCylinderPower());
        refractionMeasurement.setCylinderPower(refractionMeasurement.getCylinderPower() * -1);
        refractionMeasurement.setxAxis(refractionMeasurement.getxAxis() + 90);
        return refractionMeasurement;
    }

    public RefractionMeasurement convertRefraction(String rawUserInput) throws InvalidUserInputRefractionMeasurementException {
        RefractionMeasurement refractionMeasurement = parseUserInputToMeasurement(rawUserInput);
        return convertRefraction(refractionMeasurement);
    }

    public RefractionMeasurement parseUserInputToMeasurement(String rawUserInput) throws InvalidUserInputRefractionMeasurementException {
        String[] measurementVars = rawUserInput.split(" ");
        RefractionMeasurement refractionMeasurement;
        if (measurementVars.length != 3) {
            throw new InvalidUserInputRefractionMeasurementException(rawUserInput);
        }
        try {
            double spherePower = Double.parseDouble(measurementVars[0]);
            double cylinderPower = Double.parseDouble(measurementVars[1]);
            int xAxis = Integer.parseInt(measurementVars[2]);
            refractionMeasurement = new RefractionMeasurement(spherePower, cylinderPower, xAxis);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputRefractionMeasurementException(rawUserInput);
        }
        return refractionMeasurement;
    }
}
