package com.fxpro.vblinov.validation;

public class ValidationResult {
    private final boolean isValid;
    private final String error;

    private ValidationResult(boolean isValid, String error) {
        this.isValid = isValid;
        this.error = error;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getError() {
        return error;
    }

    public static ValidationResult ok(){
        return new ValidationResult(true, "");
    }

    public static ValidationResult error(String error){
        return new ValidationResult(false, error);
    }
}
