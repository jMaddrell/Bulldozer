package de.refactorco.Bulldozer.util;

import com.lexicalscope.jewel.cli.Option;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public interface BulldozerArgs  {
    @Option(description = "system configuration bootstrap file", defaultValue = "bulldozer.properties")
    String getConfigurationBootstrap();

    @Option(description = "postgresql server host", defaultValue = "localhost")
    String getPostgresHost();

    @Option(description = "postgresql server port", defaultValue = "5432")
    int getPostgresPort();

    @Option(description = "postgresql server user", defaultValue = "bulldozer")
    String getPostgresUser();

    @Option(description = "postgresql server password", defaultValue = "bulldozer123")
    String getPostgresPassword();

    @Option(description = "postgresql server database", defaultValue = "bulldozer")
    String getPostgresDatabase();
}
