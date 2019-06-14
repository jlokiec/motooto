package pl.motooto.webapp.service.exception;

public class EmailTakenException extends Exception {
    public EmailTakenException() {
    }

    public EmailTakenException(String message) {
        super(message);
    }

    public EmailTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailTakenException(Throwable cause) {
        super(cause);
    }

    public EmailTakenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
