package ar.com.laboratorio.steady.fabrica_revival.domain.vo;

import ar.com.laboratorio.steady.fabrica_revival.domain.exceptions.RevivalStatusRecordException;

public record RevivalStatus(String value) {

    public static RevivalStatus dormant() {
        return new RevivalStatus("Dormant");
    }

    public static RevivalStatus audited() {
        return new RevivalStatus("Audited");
    }

    public static RevivalStatus reactivated() {
        return new RevivalStatus("Reactivated");
    }

    public boolean canBeReactivated() {
        return "Audited".equals(value);
    }
    public RevivalStatus nextOnReactivation() {
        return switch (value) {
            case "Audited" -> new RevivalStatus("Reactivated");
            case "Dormant" -> throw new RevivalStatusRecordException("Debe auditarse antes de reactivar.");
            case "Reactivated" -> throw new RevivalStatusRecordException("Ya está reactivado.");
            default -> throw new RevivalStatusRecordException("Estado desconocido.");
        };
    }
}