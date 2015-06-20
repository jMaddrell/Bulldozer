package de.refactorco.Bulldozer.net.web;

import com.google.inject.servlet.GuiceFilter;
import com.netflix.governator.annotations.AutoBindSingleton;
import com.netflix.governator.annotations.Configuration;
import de.refactorco.Bulldozer.net.web.modules.BulldozerGuiceServletContextListener;
import org.apache.shiro.web.env.EnvironmentLoader;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@AutoBindSingleton
public class WebServer implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WebServer.class);

    @Configuration(value = "de.refactorco.Bulldozer.net.web.WebServer.port")
    private int port;

    @Configuration(value = "de.refactorco.Bulldozer.net.web.WebServer.host")
    private String host;

    private Thread thread;

    @Override
    public void run() {
        try {
            Server server = new Server();

            ServerConnector connector = new ServerConnector(server);
            connector.setHost(host);
            connector.setPort(port);

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase(getClass().getResource("/static-files").toExternalForm());
            resourceHandler.setDirectoriesListed(false);
            resourceHandler.setWelcomeFiles(new String[]{"index.html"});

            WebAppContext webApp = new WebAppContext();
            webApp.setContextPath("/");
            webApp.setResourceBase(getClass().getResource("/webapp").toExternalForm());
            webApp.setParentLoaderPriority(true);
            webApp.setInitParameter(EnvironmentLoader.CONFIG_LOCATIONS_PARAM, "classpath:shiro.ini");
            webApp.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
            webApp.addEventListener(new EnvironmentLoaderListener());
            webApp.addEventListener(new BulldozerGuiceServletContextListener());
            webApp.addFilter(GuiceFilter.class, "/*", null);

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resourceHandler, webApp});

            server.addConnector(connector);
            server.setHandler(handlers);

            server.start();
            server.join();
        } catch (Exception e) {
            logger.error("Failed to start", e);
            System.exit(-1);
        }
    }

    public void startServer() {
        this.thread = new Thread(this);
        this.thread.start();
    }
}
