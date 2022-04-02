package com.millward.cliapplication;

import com.millward.services.RefractionConversionService;
import com.millward.services.exceptions.InvalidUserInputRefractionMeasurementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com.millward")
public class CliApplication implements CommandLineRunner {

    private static RefractionConversionService refractionConversionService;

    @Autowired
    public CliApplication(RefractionConversionService refractionConversionService) {
        CliApplication.refractionConversionService = refractionConversionService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CliApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter measurement for conversion (Sphere Cylinder Power) or 'exit': ");
            String rawUserInput = scanner.nextLine();

            if ("exit".equals(rawUserInput)) {
                break;
            }

            String result;
            try {
                result = refractionConversionService.convertRefraction(rawUserInput).toString();
            } catch (InvalidUserInputRefractionMeasurementException e) {
                result = e.getMessage();
            }
            System.out.println(result + System.lineSeparator());
        }
    }
}
