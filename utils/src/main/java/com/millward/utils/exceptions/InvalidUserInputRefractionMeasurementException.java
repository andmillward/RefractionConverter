package com.millward.utils.exceptions;

// Make debugging easier by throwing this custom exception when there's issues with user input of refraction measurements
public class InvalidUserInputRefractionMeasurementException extends Exception {

    public InvalidUserInputRefractionMeasurementException(String rawUserInput, String errorMessage) {
        super("The user input was not parsable to a refraction measurement: " + rawUserInput + System.lineSeparator() + errorMessage);
    }
}
