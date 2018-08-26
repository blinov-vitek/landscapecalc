package com.fxpro.vblinov;

import com.fxpro.vblinov.calculator.WaterAmountCalculator;
import com.fxpro.vblinov.validation.LandscapeValidator;
import com.fxpro.vblinov.validation.ValidationException;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        WaterAmountCalculator calc = new WaterAmountCalculator(new LandscapeValidator());
        try {
            System.out.println(calc.calculateWaterAmount(parseLandscape(args)));
        } catch (ValidationException e) {
            System.out.println("incorrect landscape provided, error:\n" + e.getMessage());
        }
    }

    private static int[] parseLandscape(String[] args) {
        return Arrays.stream(args)
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
