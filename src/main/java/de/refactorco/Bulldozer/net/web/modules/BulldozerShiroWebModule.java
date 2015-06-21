package de.refactorco.Bulldozer.net.web.modules;

import com.google.inject.binder.AnnotatedBindingBuilder;
import com.google.inject.name.Names;
import de.refactorco.Bulldozer.Bulldozer;
import de.refactorco.Bulldozer.util.HazelcastCacheManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

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

        bind(SessionDAO.class).to(EnterpriseCacheSessionDAO.class);
        bind(CacheManager.class).to(HazelcastCacheManager.class);
    }

    @Override
    protected void bindSessionManager(AnnotatedBindingBuilder<SessionManager> bind) {
        bind.to(DefaultWebSessionManager.class).asEagerSingleton();
    }
}