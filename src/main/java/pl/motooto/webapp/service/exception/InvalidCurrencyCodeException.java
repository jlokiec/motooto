package pl.motooto.webapp.service.exception;

public class InvalidCurrencyCodeException extends Exception {
    public InvalidCurrencyCodeException() {
    }

    public InvalidCurrencyCodeException(String message) {
        super(message);
    }

    public InvalidCurrencyCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurrencyCodeException(Throwable cause) {
        super(cause);
    }

    public InvalidCurrencyCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
