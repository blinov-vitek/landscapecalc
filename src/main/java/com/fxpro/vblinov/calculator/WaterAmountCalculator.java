package com.fxpro.vblinov.calculator;

import com.fxpro.vblinov.validation.LandscapeValidator;
import com.fxpro.vblinov.validation.ValidationException;
import com.fxpro.vblinov.validation.ValidationResult;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

public class WaterAmountCalculator {
    private final LandscapeValidator validator;

    public WaterAmountCalculator(LandscapeValidator validator) {
        this.validator = validator;
    }

    public long calculateWaterAmount(int[] landscape) throws ValidationException {
        ValidationResult validationResult = validator.validate(landscape);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getError());
        }

        int[] localMaximas = getLocalMaximas(landscape);
        if (localMaximas.length < 2) {
            return 0;
        } else {
            long waterAmount = 0;
            int currentMaxima = 0, nextMaxima, pitStart, pitEnd;
            while (currentMaxima < localMaximas.length - 1) {
                nextMaxima = getNextMaxima(currentMaxima, localMaximas, landscape);
                pitStart = localMaximas[currentMaxima];
                pitEnd = localMaximas[nextMaxima];
                waterAmount += calculatePitWaterAmount(pitStart, pitEnd, landscape);
                currentMaxima = nextMaxima;
            }
            return waterAmount;
        }
    }

    private long calculatePitWaterAmount(int start, int end, int[] landscape) {
        int waterHeight = Math.min(landscape[start], landscape[end]);
        return IntStream
                .range(start + 1, end)
                .map(i -> waterHeight - landscape[i])
                .sum();
    }

    private int[] getLocalMaximas(int[] landscape) {
        return IntStream
                .range(0, landscape.length)
                .filter(i -> isLocalMaxima(landscape, i))
                .toArray();
    }

    private boolean isLocalMaxima(int[] landscape, int i) {
        return ((i == 0) || (landscape[i - 1] < landscape[i])) &&
                ((i == landscape.length - 1) || (landscape[i + 1] <= landscape[i]));
    }

    private int getNextMaxima(int currentMaxima, int[] maximas, int[] landscape) {
        return getNextGreaterOrEqMaxima(currentMaxima, maximas, landscape)
                .orElseGet(() -> getNextMaxMaxima(currentMaxima, maximas, landscape));
    }

    private Optional<Integer> getNextGreaterOrEqMaxima(int currentMaxima, int[] maximas, int[] landscape) {
        int i = currentMaxima + 1;
        while (i < maximas.length) {
            if (isGreaterOrEqMaxima(currentMaxima, maximas, landscape, i)) {
                return Optional.of(i);
            }
            i++;
        }
        return Optional.empty();
    }

    private int getNextMaxMaxima(int currentMaxima, int[] maximas, int[] landscape) {
        return IntStream
                .range(currentMaxima + 1, maximas.length)
                .boxed()
                .max(Comparator.comparingInt(i -> landscape[maximas[i]]))
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    private boolean isGreaterOrEqMaxima(int currentMaxima, int[] maximas, int[] landscape, int i) {
        return landscape[maximas[i]] >= landscape[maximas[currentMaxima]];
    }
}
