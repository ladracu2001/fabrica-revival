package ar.com.laboratorio.steady.fabrica_revival.domain.exceptions;

public class FactoryCodeRecordException extends RuntimeException {
    public FactoryCodeRecordException(String message) {
        super(message);
    }

    public FactoryCodeRecordException(String message, Throwable cause) {
        super(message, cause);
    }

}
