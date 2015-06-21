package de.refactorco.Bulldozer.util;

import com.google.inject.Inject;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.*;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;

import java.util.concurrent.ConcurrentMap;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class HazelcastCacheManager implements CacheManager, Initializable, Destroyable {
    @Inject
    private HazelcastCache hazelcastCache;

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        ConcurrentMap<K,V> map = ensureHazelcastInstance().getMap(name);
        return new MapCache<K, V>(name, map);
    }

    protected HazelcastInstance ensureHazelcastInstance() {
        return this.hazelcastCache.getHazelcastInstance();
    }


    public void destroy() throws Exception {
        //NO-OP
    }

    @Override
    public void init() throws ShiroException {
        hazelcastCache.init();
    }
}