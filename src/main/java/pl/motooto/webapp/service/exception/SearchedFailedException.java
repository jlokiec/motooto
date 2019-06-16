package pl.motooto.webapp.service.exception;

public class SearchedFailedException extends Exception {

    public SearchedFailedException() {
    }

    public SearchedFailedException(String message) {
        super(message);
    }

    public SearchedFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchedFailedException(Throwable cause) {
        super(cause);
    }

    public SearchedFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

