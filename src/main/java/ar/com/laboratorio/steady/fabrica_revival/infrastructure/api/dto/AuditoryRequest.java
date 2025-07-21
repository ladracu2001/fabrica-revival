package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuditoryRequest(
    @NotBlank(message = "La observación no puede estar vacía")
    @Size(min = 5, max = 300, message = "La observación debe tener entre 5 y 300 caracteres")    
    String observacion) {
}
