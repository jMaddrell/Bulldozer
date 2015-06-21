package de.refactorco.Bulldozer.util;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.netflix.governator.annotations.AutoBindSingleton;

/**
 * Bulldozer,
 * Edited: 21/06/15.
 */
@AutoBindSingleton
public class HazelcastCache {
    private Config config;
    private HazelcastInstance hazelcastInstance;

    public HazelcastCache() {
        init();
    }

    public void init() {
        if (this.hazelcastInstance == null) {
            this.hazelcastInstance = Hazelcast.newHazelcastInstance(this.config);
        }
    }

    public HazelcastInstance getHazelcastInstance() {
        init();
        return hazelcastInstance;
    }

    public Config getConfig() {
        return config;
    }
}
