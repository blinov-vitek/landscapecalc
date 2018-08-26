package com.fxpro.vblinov.calculator;

import com.fxpro.vblinov.validation.LandscapeValidator;
import com.fxpro.vblinov.validation.ValidationException;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WaterAmountCalculatorTest {
    private WaterAmountCalculator calculator = new WaterAmountCalculator(new LandscapeValidator());

    @DataProvider
    public static Object[][] landscapeWithoutHills() {
        return new Object[][]{
                {new int[]{1}, 0},
                {new int[]{0}, 0},
                {new int[]{1, 2}, 0},
                {new int[]{1, 1}, 0},
                {new int[]{2, 1}, 0}
        };
    }

    @DataProvider
    public static Object[][] plainLandscape() {
        return new Object[][]{
                {new int[]{1, 1, 1}, 0},
                {new int[]{0, 0, 0}, 0}
        };
    }

    @DataProvider
    public static Object[][] landscapeWithOneHill() {
        return new Object[][]{
                {new int[]{0, 2, 0}, 0},
                {new int[]{2, 2, 0}, 0},
                {new int[]{0, 2, 2}, 0}
        };
    }

    @DataProvider
    public static Object[][] landscapeWithPits() {
        return new Object[][]{
                {new int[]{1, 0, 1}, 1},
                {new int[]{5, 0, 1}, 1},
                {new int[]{1, 0, 5}, 1},
                {new int[]{1, 0, 0, 5}, 2},
                {new int[]{5, 0, 0, 1}, 2},
                {new int[]{5, 0, 0, 1, 0, 0, 5}, 24},
                {new int[]{5, 0, 0, 1, 0, 0, 5, 0, 0, 0}, 24},
                {new int[]{5, 0, 0, 1, 0, 0, 5, 0, 0, 7}, 34}
        };
    }

    @Test(dataProvider = "landscapeWithoutHills")
    public void testWithoutHills(int[] landscape, long expectedWaterAmount) throws ValidationException {
        assertEquals(calculator.calculateWaterAmount(landscape), expectedWaterAmount);
    }

    @Test(dataProvider = "plainLandscape")
    public void testPlainLandscape(int[] landscape, long expectedWaterAmount) throws ValidationException {
        assertEquals(calculator.calculateWaterAmount(landscape), expectedWaterAmount);
    }

    @Test(dataProvider = "landscapeWithOneHill")
    public void testLandscapeWithOneHill(int[] landscape, long expectedWaterAmount) throws ValidationException {
        assertEquals(calculator.calculateWaterAmount(landscape), expectedWaterAmount);
    }

    @Test(dataProvider = "landscapeWithPits")
    public void testLandscapeWithPits(int[] landscape, long expectedWaterAmount) throws ValidationException {
        assertEquals(calculator.calculateWaterAmount(landscape), expectedWaterAmount);
    }
}