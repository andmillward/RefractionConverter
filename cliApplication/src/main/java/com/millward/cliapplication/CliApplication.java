package com.millward.cliapplication;

import com.millward.utils.RefractionConversionUtils;
import com.millward.utils.exceptions.InvalidUserInputRefractionMeasurementException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

// Entry point for converting measurements via the command line.
@ComponentScan("com.millward")
public class CliApplication implements CommandLineRunner {

    public static void main(String[] args) {
        CliApplication cliApplication = new CliApplication();
        cliApplication.run();
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
                result = RefractionConversionUtils.convertRefraction(rawUserInput).toString();
            } catch (InvalidUserInputRefractionMeasurementException e) {
                result = e.getMessage();
            }
            System.out.println(result + System.lineSeparator());
        }
    }
}
