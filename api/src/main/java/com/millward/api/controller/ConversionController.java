package com.millward.api.controller;

import com.millward.models.entities.RefractionMeasurement;
import com.millward.utils.RefractionConversionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// Endpoints for converting refraction measurements for applications
@RestController
public class ConversionController {

    // List to support batch conversions
    @PostMapping(value = "/convert")
    public List<RefractionMeasurement> getConvertedMeasurements(@RequestBody List<RefractionMeasurement> refractionMeasurements) {
        return refractionMeasurements.stream().map(RefractionConversionUtils::convertRefraction).collect(Collectors.toList());
    }
}
