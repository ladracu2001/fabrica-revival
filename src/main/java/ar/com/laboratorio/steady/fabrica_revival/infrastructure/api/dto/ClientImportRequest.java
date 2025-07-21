package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientImportRequest(
    @NotBlank(message = "El código de planta no puede estar vacío")
    @Pattern(regexp = "[A-Z]{3}-\\\\d{4}", message = "Formato inválido para el código de planta")
    String factoryCode) {
}
