package com.millward.services.exceptions;

public class InvalidUserInputRefractionMeasurementException extends Exception {
    public InvalidUserInputRefractionMeasurementException(String rawUserInput) {
        super("The user input was not parsable to a refraction measurement: " + rawUserInput);
    }
}
