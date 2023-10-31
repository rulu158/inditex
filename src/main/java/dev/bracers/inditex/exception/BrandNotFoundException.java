package dev.bracers.inditex.exception;

import java.io.Serial;

public class BrandNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = -5721043483746724478L;

    public BrandNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
