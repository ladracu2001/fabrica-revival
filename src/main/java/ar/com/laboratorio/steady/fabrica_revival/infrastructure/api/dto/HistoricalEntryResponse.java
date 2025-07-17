package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto;

import java.time.LocalDateTime;

public record HistoricalEntryResponse(String type, String detail, LocalDateTime timestamp) {

}
