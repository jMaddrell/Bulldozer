package de.refactorco.Bulldozer;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.impossibl.postgres.jdbc.PGDataSource;
import com.lexicalscope.jewel.cli.CliFactory;
import com.lexicalscope.jewel.cli.HelpRequestedException;
import com.netflix.config.*;
import com.netflix.config.sources.JDBCConfigurationSource;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.netflix.governator.configuration.ArchaiusConfigurationProvider;
import com.netflix.governator.guice.BootstrapBinder;
import com.netflix.governator.guice.BootstrapModule;
import com.netflix.governator.guice.LifecycleInjector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.refactorco.Bulldozer.net.web.WebServer;
import de.refactorco.Bulldozer.util.BulldozerArgs;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@AutoBindSingleton
public class Bulldozer {
    private static Logger logger = LoggerFactory.getLogger(Bulldozer.class);
    private static Bulldozer instance;

    @Inject
    private Injector injector;

    @Inject
    private WebServer webServer;

    private HikariDataSource dataSource;

    @Inject
    protected Bulldozer(Injector injector) {
        this.injector = injector;
    }

    public static void main(String[] args) throws ConfigurationException {
        System.out.println();
        System.out.println("______       _ _     _                   ");
        System.out.println("| ___ \\     | | |   | |                  ");
        System.out.println("| |_/ /_   _| | | __| | ___ _______ _ __ ");
        System.out.println("| ___ \\ | | | | |/ _` |/ _ \\_  / _ \\ '__|");
        System.out.println("| |_/ / |_| | | | (_| | (_) / /  __/ |   ");
        System.out.println("\\____/ \\__,_|_|_|\\__,_|\\___/___\\___|_|   ");
        System.out.println();

        try {
            final BulldozerArgs arguments = CliFactory.parseArguments(BulldozerArgs.class, args);

            logger.info("Initializing");

            HikariDataSource hikariDataSource = initializeDataSource(arguments);
            initializeConfiguration(arguments, hikariDataSource);

            Injector injector = initializeInjector();
            instance = injector.getInstance(Bulldozer.class);
            instance.dataSource = hikariDataSource;
            instance.initializeWebServer();
        } catch (HelpRequestedException e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
    }

    private static HikariDataSource initializeDataSource(BulldozerArgs arguments) {
        logger.info("Initializing data source");
        HikariConfig hikariConfig = new HikariConfig();

        PGDataSource dataSource = new PGDataSource();
        dataSource.setHost(arguments.getPostgresHost());
        dataSource.setPort(arguments.getPostgresPort());
        dataSource.setUser(arguments.getPostgresUser());
        dataSource.setPassword(arguments.getPostgresPassword());
        dataSource.setDatabase(arguments.getPostgresDatabase());

        hikariConfig.setDataSource(dataSource);
        hikariConfig.setUsername(arguments.getPostgresUser());
        hikariConfig.setPassword(arguments.getPostgresPassword());

        return new HikariDataSource(hikariConfig);
    }

    private static Injector initializeInjector() {
        logger.info("Creating lifecycle injector");

        //Create a custom LifeCycle injector so that Archaius can configure the application with Guice
        return LifecycleInjector
                .builder()
                .usingBasePackages("de.refactorco.Bulldozer")
                .withBootstrapModule(
                        new BootstrapModule() {
                            @Override
                            public void configure(BootstrapBinder binder) {
                                binder.bindConfigurationProvider().toInstance(ArchaiusConfigurationProvider.builder().build());
                            }
                        }
                )
                .build()
                .createInjector();
    }

    private static void initializeConfiguration(BulldozerArgs arguments, DataSource dataSource) throws ConfigurationException {
        logger.info("Loading configuration from {}, environment and database", arguments.getConfigurationBootstrap());
        ConcurrentMapConfiguration propertiesConfig = new ConcurrentMapConfiguration((new PropertiesConfiguration(arguments.getConfigurationBootstrap())));
        ConcurrentMapConfiguration systemConfig = new ConcurrentMapConfiguration(new SystemConfiguration());

        PolledConfigurationSource polledConfigurationSource = new JDBCConfigurationSource(
                dataSource,
                "Select distinct property_key, property_value from server_properties",
                "property_key",
                "property_value"
        );

        DynamicConfiguration dynamicConfiguration = new DynamicConfiguration(polledConfigurationSource,
                new FixedDelayPollingScheduler(100, 1000, true));

        ConcurrentCompositeConfiguration finalConfig = new ConcurrentCompositeConfiguration();

        finalConfig.addConfiguration(propertiesConfig, "propertiesConfig");
        finalConfig.addConfiguration(systemConfig, "systemConfig");
        finalConfig.addConfiguration(dynamicConfiguration, "dynamicConfig");

        ConfigurationManager.install(finalConfig);
    }

    public static Bulldozer getInstance() {
        return instance;
    }

    private void initializeWebServer() {
        logger.info("Initializing WebServer");
        webServer.startServer();
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public Injector getInjector() {
        return injector;
    }
}
