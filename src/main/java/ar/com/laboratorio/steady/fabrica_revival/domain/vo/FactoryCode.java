package ar.com.laboratorio.steady.fabrica_revival.domain.vo;

import java.util.regex.Pattern;
import static java.util.Objects.isNull;
import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.FactoryCodeRecordException;

public record FactoryCode(String value) {

private static final Pattern VALID_PATTERN = Pattern.compile("[A-Z]{3}-\\d{4}");

    public FactoryCode {
        if (isNull(value) || !VALID_PATTERN.matcher(value).matches()) {
            throw new FactoryCodeRecordException("Formato inválido para FactoryCode. Se espera 'AAA-0000'");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
