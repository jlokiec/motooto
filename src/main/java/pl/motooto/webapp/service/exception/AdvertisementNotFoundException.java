package pl.motooto.webapp.service.exception;

public class AdvertisementNotFoundException extends Exception {
    public AdvertisementNotFoundException() {
    }

    public AdvertisementNotFoundException(String message) {
        super(message);
    }

    public AdvertisementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdvertisementNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdvertisementNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
