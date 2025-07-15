package ar.com.laboratorio.steady.fabrica_revival.domain.exceptions;

public class RevivalStatusRecordException extends RuntimeException {
    public RevivalStatusRecordException(String message) {
        super(message);
    }

    public RevivalStatusRecordException(String message, Throwable cause) {
        super(message, cause);
    }

}
