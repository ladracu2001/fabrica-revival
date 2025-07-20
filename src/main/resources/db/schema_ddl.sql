CREATE TABLE IF NOT EXISTS legacyclient (
    id BINARY(16) NOT NULL PRIMARY KEY,
    factorycode VARCHAR(255) NOT NULL,
    revivalstatus VARCHAR(255) NOT NULL,
    createdat DATETIME(6) NOT NULL
) ENGINE=MEMORY;

CREATE TABLE IF NOT EXISTS clienthistoryentries (
    clientid BINARY(16) NOT NULL,
    eventid BINARY(16),
    eventtype VARCHAR(255),
    eventdetail VARCHAR(500),
    eventtimestamp DATETIME(6),
    PRIMARY KEY (clientid, eventtimestamp),
    CONSTRAINT fk_clienthistoryentries_legacyclient
        FOREIGN KEY (clientid) REFERENCES legacyclient(id)
) ENGINE=MEMORY;