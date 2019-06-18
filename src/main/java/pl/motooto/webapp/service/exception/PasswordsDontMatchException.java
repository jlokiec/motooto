package pl.motooto.webapp.service.exception;

public class PasswordsDontMatchException extends Exception {
    public PasswordsDontMatchException() {
    }

    public PasswordsDontMatchException(String message) {
        super(message);
    }

    public PasswordsDontMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsDontMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordsDontMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
