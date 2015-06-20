package de.refactorco.Bulldozer.util;

import com.lexicalscope.jewel.cli.Option;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public interface BulldozerArgs  {
    @Option(description = "system configuration bootstrap file", defaultValue = "bulldozer.properties", shortName = "p")
    String getConfigurationBootstrap();

    @Option(description = "postgresql server host", defaultValue = "localhost", shortName = "dbhost")
    String getPostgresHost();

    @Option(description = "postgresql server port", defaultValue = "5432", shortName = "dbport")
    int getPostgresPort();

    @Option(description = "postgresql server user", defaultValue = "bulldozer", shortName = "dbuser")
    String getPostgresUser();

    @Option(description = "postgresql server password", defaultValue = "bulldozer123", shortName = "dbpass")
    String getPostgresPassword();

    @Option(description = "postgresql server database", defaultValue = "bulldozer", shortName = "dbname")
    String getPostgresDatabase();
}
