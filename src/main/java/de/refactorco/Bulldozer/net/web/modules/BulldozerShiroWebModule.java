package de.refactorco.Bulldozer.net.web.modules;

import com.google.inject.name.Names;
import de.refactorco.Bulldozer.Bulldozer;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import javax.servlet.ServletContext;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class BulldozerShiroWebModule extends ShiroWebModule {

    public BulldozerShiroWebModule(ServletContext servletContext) {
        super(servletContext);
    }

    @Override
    protected void configureShiroWeb() {
        bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(30000L);

        DefaultPasswordService passwordService = new DefaultPasswordService();
        DefaultHashService hashService = new DefaultHashService();
        PasswordMatcher passwordMatcher = new PasswordMatcher();

        passwordService.setHashService(hashService);
        passwordMatcher.setPasswordService(passwordService);

        JdbcRealm realm = new JdbcRealm();
        realm.setCredentialsMatcher(passwordMatcher);
        realm.setDataSource(Bulldozer.getInstance().getDataSource());
        bindRealm().toInstance(realm);
    }

}