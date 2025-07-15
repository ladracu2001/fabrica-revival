package ar.com.laboratorio.steady.fabrica_revival.domain.exceptions;

public class ClientHistoryRecordException extends RuntimeException {
    public ClientHistoryRecordException(String message) {
        super(message);
    }

    public ClientHistoryRecordException(String message, Throwable cause) {
        super(message, cause);
    }

}
