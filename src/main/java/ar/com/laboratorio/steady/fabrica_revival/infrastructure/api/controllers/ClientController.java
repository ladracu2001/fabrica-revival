package ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.laboratorio.steady.fabrica_revival.application.AuditClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ImportLegacyClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.ReactivateClientUseCase;
import ar.com.laboratorio.steady.fabrica_revival.application.RetrieveClientHistoryUseCase;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto.AuditoryRequest;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto.ClientImportRequest;
import ar.com.laboratorio.steady.fabrica_revival.infrastructure.api.dto.HistoricalEntryResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ImportLegacyClientUseCase legacyClientImporter;
    private final AuditClientUseCase clientAuditor;
    private final ReactivateClientUseCase clientReactivator;
    private final RetrieveClientHistoryUseCase retrieveClientHistoryUseCase;

    public ClientController(ImportLegacyClientUseCase legacyClientImporter,
                            AuditClientUseCase clientAuditor,
                            ReactivateClientUseCase clientReactivator,
                            RetrieveClientHistoryUseCase retrieveClientHistoryUseCase) {
        this.legacyClientImporter = legacyClientImporter;
        this.clientAuditor = clientAuditor;
        this.clientReactivator = clientReactivator;
        this.retrieveClientHistoryUseCase = retrieveClientHistoryUseCase;
    }

    @PostMapping("/import")
    public ResponseEntity<UUID> importClient(@RequestBody @Valid ClientImportRequest dto) {
        var id = legacyClientImporter.importLegacyClient(dto.factoryCode());
        return ResponseEntity.ok(id);
    }
    @PostMapping("/{id}/audit")
    public ResponseEntity<Void> auditClient(@PathVariable UUID id, @RequestBody @Valid AuditoryRequest request) {
        clientAuditor.audit(id, request.observacion());
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id}/reactivate")
    public ResponseEntity<Void> reactivate(@PathVariable UUID id) {
        clientReactivator.reactivate(id);        
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/history")
    public ResponseEntity<List<HistoricalEntryResponse>> historic(@PathVariable UUID id) {
        var events = retrieveClientHistoryUseCase.getHistory(id);
        var response = events.stream()
                .map( n -> new HistoricalEntryResponse(
                        n.type().name(),
                        n.details(),
                        n.timestamp()))
                .toList();
        return ResponseEntity.ok(response);
    }
}
