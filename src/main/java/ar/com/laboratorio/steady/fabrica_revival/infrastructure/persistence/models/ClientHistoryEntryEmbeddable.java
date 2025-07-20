package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models;

import java.time.LocalDateTime;
import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.events.ClientEventType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
@Embeddable
public class ClientHistoryEntryEmbeddable {

    @Column(name = "eventid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "eventtype")
    private ClientEventType type;

    @Column(name = "eventdetail", length = 500)
    private String detail;

    @Column(name = "eventtimestamp")
    private LocalDateTime timestamp;

    protected ClientHistoryEntryEmbeddable() { }

    public ClientHistoryEntryEmbeddable(UUID id, ClientEventType type,
                                        String detail, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.detail = detail;
        this.timestamp = timestamp;
    }
}
