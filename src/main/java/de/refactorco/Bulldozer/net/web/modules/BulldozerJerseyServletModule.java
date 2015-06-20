package de.refactorco.Bulldozer.net.web.modules;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.shiro.guice.web.GuiceShiroFilter;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.eclipse.jetty.servlet.DefaultServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class BulldozerJerseyServletModule extends JerseyServletModule {
    private static Map<String, String> params = new HashMap<>();

    static {
        params.put("com.sun.jersey.config.property.packages", "de.refactorco.Bulldozer.net.web.resources");
    }

    @Override
    protected void configureServlets() {
        bindings();
        filters();
    }

    private void bindings() {
        bind(GuiceContainer.class).asEagerSingleton();
        bind(DefaultServlet.class).asEagerSingleton();
        bind(JacksonJaxbJsonProvider.class).asEagerSingleton();

        serve("/*").with(GuiceContainer.class, params);
    }

    private void filters() {
        filter("/*").through(GuiceShiroFilter.class);
    }

}