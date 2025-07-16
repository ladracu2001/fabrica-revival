package ar.com.laboratorio.steady.fabrica_revival.application;

import java.util.UUID;

import ar.com.laboratorio.steady.fabrica_revival.domain.contracts.LegacyClientImporter;

public class ImportLegacyClientUseCase implements LegacyClientImporter {

    @Override
    public UUID importLegacyClient(String factoryCode) {
        // Implementación del caso de uso para importar un cliente legado
        // Aquí se debería incluir la lógica necesaria para realizar la importación
        throw new UnsupportedOperationException("Unimplemented method 'importLegacyClient'");
    }
}
