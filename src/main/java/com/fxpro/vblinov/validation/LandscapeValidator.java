package com.fxpro.vblinov.validation;

public class LandscapeValidator {

    private static final int MAX_LANDSCAPE_POSITIONS = 32000;
    private static final int MIN_HEIGHT = 0;
    private static final int MAX_HEIGHT = 32000;

    public ValidationResult validate(int[] landscape) {
        if (landscape.length == 0) {
            return ValidationResult.error("no landscape provided");
        }

        if (landscape.length > MAX_LANDSCAPE_POSITIONS) {
            return ValidationResult.error("maximum number of positions is 32000");
        }

        for (int i = 0; i < landscape.length; i++) {
            if (landscape[i] < MIN_HEIGHT || landscape[i] > MAX_HEIGHT) {
                return ValidationResult.error(
                        String.format("Position %d height is %d, must be between 0 and 32000", i, landscape[i]));
            }
        }

        return ValidationResult.ok();
    }
}
