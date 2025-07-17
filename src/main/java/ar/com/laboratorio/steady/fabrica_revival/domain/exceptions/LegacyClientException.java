package ar.com.laboratorio.steady.fabrica_revival.domain.exceptions;

public class LegacyClientException extends RuntimeException {

    public LegacyClientException(String message) {
        super(message);
    }

    public LegacyClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public LegacyClientException(Throwable cause) {
        super(cause);
    }

}
