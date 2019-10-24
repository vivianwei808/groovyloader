package groovyloader;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class XXXRegistry {
    private static final XXXRegistry INSTANCE = new XXXRegistry();

    public static final XXXRegistry instance() {
        return INSTANCE;
    }

    private final ConcurrentHashMap<String, XXX> xxxs = new ConcurrentHashMap<String, XXX>();

    private XXXRegistry() {
    }

    public XXX remove(String key) {
        return this.xxxs.remove(key);
    }

    public XXX get(String key) {
        return this.xxxs.get(key);
    }

    public void put(String key, XXX filter) {
        this.xxxs.putIfAbsent(key, filter);
    }

    public int size() {
        return this.xxxs.size();
    }

    public Collection<XXX> getAllxxxs() {
        return this.xxxs.values();
    }
}
