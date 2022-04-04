package com.millward.api;

import com.millward.models.entities.RefractionMeasurement;
import com.millward.utils.RefractionConversionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Entry point for server that hosts web API
@SpringBootApplication
@ComponentScan("com.millward")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        RefractionMeasurement refractionMeasurement = new RefractionMeasurement(.25, 1.25, 170);
        System.out.println(RefractionConversionUtils.convertRefraction(refractionMeasurement));
    }

}
