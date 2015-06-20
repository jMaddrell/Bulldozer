package de.refactorco.Bulldozer.util;

import com.lexicalscope.jewel.cli.Option;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public interface BulldozerArgs  {
    @Option(description = "system configuration bootstrap file", defaultValue = "bulldozer.properties", shortName = "p")
    String getConfigurationBootstrap();

    @Option(description = "postgresql server host", defaultValue = "localhost", shortName = "s")
    String getPostgresHost();

    @Option(description = "postgresql server port", defaultValue = "5432", shortName = "i")
    int getPostgresPort();

    @Option(description = "postgresql server user", defaultValue = "bulldozer", shortName = "u")
    String getPostgresUser();

    @Option(description = "postgresql server password", defaultValue = "bulldozer123", shortName = "x")
    String getPostgresPassword();

    @Option(description = "postgresql server database", defaultValue = "bulldozer", shortName = "n")
    String getPostgresDatabase();

    @Option(helpRequest = true, description = "display help", shortName = "h")
    boolean getHelp();
}
