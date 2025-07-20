package ar.com.laboratorio.steady.fabrica_revival.infrastructure.persistence.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "legacyclient", schema = "industrial")
public class LegacyClientEntity {

    @Id
    private UUID id;

    @Column(name = "factorycode", nullable = false)
    private String factoryCode;

    @Column(name = "revivalstatus", nullable = false)
    private String revivalStatus;

    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(name = "clienthistoryentries", joinColumns = @JoinColumn(name = "clientid"))
    private List<ClientHistoryEntryEmbeddable> historyEntries = new ArrayList<>();

    protected LegacyClientEntity() { }

    public LegacyClientEntity(UUID id, String factoryCode, String revivalStatus,
                              LocalDateTime createdAt, List<ClientHistoryEntryEmbeddable> historyEntries) {
        this.id = id;
        this.factoryCode = factoryCode;
        this.revivalStatus = revivalStatus;
        this.createdAt = createdAt;
        this.historyEntries = historyEntries;
    }
}
