package dev.bracers.inditex.exception;

import java.io.Serial;

public class PriceNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 4566904890895928974L;

    public PriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
