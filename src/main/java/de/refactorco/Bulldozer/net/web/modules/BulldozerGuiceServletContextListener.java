package de.refactorco.Bulldozer.net.web.modules;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import de.refactorco.Bulldozer.Bulldozer;
import org.apache.shiro.guice.aop.ShiroAopModule;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class BulldozerGuiceServletContextListener extends GuiceServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        super.contextInitialized(servletContextEvent);
    }

    @Override
    protected Injector getInjector() {
        return Bulldozer.getInstance().getInjector().createChildInjector(
                new BulldozerJerseyServletModule(),
                new BulldozerShiroWebModule(this.servletContext),
                new ShiroAopModule()
        );
    }

}