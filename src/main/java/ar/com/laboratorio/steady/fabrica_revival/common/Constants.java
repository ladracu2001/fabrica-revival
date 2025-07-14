package ar.com.laboratorio.steady.fabrica_revival.common;

public class Constants {

    private Constants() {
        // Prevent instantiation
    }
    public static final String FACTORY_CODE_PATTERN = "[A-Z]{3}-\\d{4}";
    public static final String REVIVAL_STATUS_DORMANT = "Dormant";
    public static final String REVIVAL_STATUS_AUDITED = "Audited";
    public static final String REVIVAL_STATUS_REACTIVATED = "Reactivated";
    public static final String REVIVAL_STATUS_UNKNOWN = "Unknown";
    public static final String REVIVAL_STATUS_ERROR_AUDIT_FIRST = "Debe auditarse antes de reactivar.";
    public static final String REVIVAL_STATUS_ERROR_ALREADY_REACTIVATED = "Ya está reactivado.";
    public static final String REVIVAL_STATUS_ERROR_UNKNOWN = "Estado desconocido.";
    public static final String FACTORY_CODE_FORMAT_ERROR = "Formato inválido para FactoryCode. Se espera 'AAA-0000'";
    public static final String LEGACY_CLIENT_ERROR = "Error al procesar LegacyClient";
    public static final String LEGACY_CLIENT_SUCCESS = "LegacyClient procesado correctamente";
    public static final String LEGACY_CLIENT_NOT_FOUND = "LegacyClient no encontrado";
    public static final String LEGACY_CLIENT_INVALID = "LegacyClient inválido";
}
