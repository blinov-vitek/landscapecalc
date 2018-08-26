package com.fxpro.vblinov.validation;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class LandscapeValidatorTest {
    LandscapeValidator validator = new LandscapeValidator();

    @Test
    public void testNoLandscape(){
        ValidationResult result = validator.validate(new int[]{});
        assertFalse(result.isValid());
    }

    @Test
    public void testTooMuchPositions(){
        ValidationResult result = validator.validate(new int[32001]);
        assertFalse(result.isValid());
    }

    @Test
    public void testHeightLessThanZero(){
        ValidationResult result = validator.validate(new int[]{1,2,3,-1,4});
        assertFalse(result.isValid());
    }

    @Test
    public void testHeightTooBig(){
        ValidationResult result = validator.validate(new int[]{1,2,3,32001,4});
        assertFalse(result.isValid());
    }
}