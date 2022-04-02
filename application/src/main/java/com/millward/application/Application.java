package com.millward.application;

import com.millward.repository.entities.RefractionMeasurement;
import com.millward.services.RefractionConversionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.millward")
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        RefractionConversionService refractionConversionService = context.getBean(RefractionConversionService.class);
        RefractionMeasurement refractionMeasurement = new RefractionMeasurement(.25, 1.25, 170);
        System.out.println(refractionConversionService.convertRefraction(refractionMeasurement));
    }

}
