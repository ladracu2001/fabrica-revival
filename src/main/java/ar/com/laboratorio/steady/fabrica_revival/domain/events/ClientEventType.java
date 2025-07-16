package ar.com.laboratorio.steady.fabrica_revival.domain.events;

public enum ClientEventType {
    IMPORTED, AUDITED, REACTIVATED,
    STATE_CHANGED,
    KAFKA_RECEIVED,
    KAFKA_PUBLISHED;

    public boolean isAuditable() {
        return switch (this) {
            case IMPORTED, AUDITED, REACTIVATED, STATE_CHANGED -> true;
            default -> false;
        };
    }
    public String topicName() {
        return switch (this) {
            case IMPORTED        -> "client.legacy.imported";
            case AUDITED         -> "client.audit.logged";
            case REACTIVATED     -> "client.reactivation.requested";
            case STATE_CHANGED   -> "client.status.transitioned";
            case KAFKA_RECEIVED  -> "system.kafka.inbound";
            case KAFKA_PUBLISHED -> "system.kafka.outbound";
        };
    }
}
