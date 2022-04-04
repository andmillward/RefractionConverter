package com.millward.api.controller;

import com.millward.models.entities.RefractionMeasurement;
import com.millward.utils.RefractionConversionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

// Endpoints for converting refraction measurements for applications
@RestController
public class ConversionController {

    // List to support batch conversions
    @PostMapping(value = "/convert")
    public List<RefractionMeasurement> convertedMeasurements(@RequestBody List<RefractionMeasurement> refractionMeasurements) {
        return refractionMeasurements.stream().map(RefractionConversionUtils::convertRefraction).collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Invalid Input: " + ex.getMessage());
    }
}
