package ar.com.laboratorio.steady.fabrica_revival.domain;

import java.util.regex.Pattern;

public record FactoryCode(String value) {
private static final Pattern VALID_PATTERN = Pattern.compile("[A-Z]{3}-\\d{4}");

    public FactoryCode {
        if (value == null || !VALID_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Formato inválido para FactoryCode. Se espera 'AAA-0000'");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
