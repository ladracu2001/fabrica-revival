package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.exceptions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.ClientHistoryRecordException;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.FactoryCodeRecordException;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.LegacyClientException;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.RevivalStatusRecordException;

@RestControllerAdvice
public class GlobalHandlerException extends Exception{

    private static final long serialVersionUID = 1L;
    private static final String LEVEL_MESSAGE = "error";

    @ExceptionHandler(ClientHistoryRecordException.class)
    public ResponseEntity<Map<String, String>> handleClientHistoryRecordException(ClientHistoryRecordException e) {
        return ResponseEntity.status(404).body(Map.of(LEVEL_MESSAGE, e.getMessage()));
    }
    @ExceptionHandler(FactoryCodeRecordException.class)
    public ResponseEntity<Map<String, String>> handleFactoryCodeRecordException(FactoryCodeRecordException e) {
        return ResponseEntity.status(404).body(Map.of(LEVEL_MESSAGE, e.getMessage()));
    }
    @ExceptionHandler(LegacyClientException.class)
    public ResponseEntity<Map<String, String>> handleLegacyClientException(LegacyClientException e) {
        return ResponseEntity.status(404).body(Map.of(LEVEL_MESSAGE, e.getMessage()));
    }
    @ExceptionHandler(RevivalStatusRecordException.class)
    public ResponseEntity<Map<String, String>> handleRevivalStatusRecordException(RevivalStatusRecordException e) {
        return ResponseEntity.status(404).body(Map.of(LEVEL_MESSAGE, e.getMessage()));
    }

}
