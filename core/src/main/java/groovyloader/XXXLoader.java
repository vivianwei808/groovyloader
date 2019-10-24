package groovyloader;

import groovyloader.groovy.DynamicCodeCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentHashMap;

public class XXXLoader {
    final static XXXLoader INSTANCE = new XXXLoader();

    private static final Logger LOG = LoggerFactory.getLogger(XXXLoader.class);

    private final ConcurrentHashMap<String, Long> xxxClassLastModified = new ConcurrentHashMap<String, Long>();
    private final ConcurrentHashMap<String, String> xxxClassCode = new ConcurrentHashMap<String, String>();
    private final ConcurrentHashMap<String, String> xxxCheck = new ConcurrentHashMap<String, String>();

    private XXXRegistry xxxRegistry = XXXRegistry.instance();

    static DynamicCodeCompiler COMPILER;

    static XXXFactory XXX_FACTORY = new DefaultXXXFactory();

    /**
     * Sets a Dynamic Code Compiler
     *
     * @param compiler
     */
    public void setCompiler(DynamicCodeCompiler compiler) {
        COMPILER = compiler;
    }

    // overidden by tests
    public void setXXXRegistry(XXXRegistry r) {
        this.xxxRegistry = r;
    }

    /**
     * Sets a XXXFactory
     *
     * @param factory
     */
    public void setXXXFactory(XXXFactory factory) {
        XXX_FACTORY = factory;
    }

    /**
     * @return Singleton XXXLoader
     */
    public static XXXLoader getInstance() {
        return INSTANCE;
    }

    /**
     * Given source and name will compile and store the xxx if it detects that the xxx code has changed or
     * the xxx doesn't exist. Otherwise it will return an instance of the requested XXX
     *
     * @param sCode source code
     * @param sName name of the xxx
     * @return the XXX
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public XXX getFilter(String sCode, String sName) throws Exception {

        if (xxxCheck.get(sName) == null) {
            xxxCheck.putIfAbsent(sName, sName);
            if (!sCode.equals(xxxClassCode.get(sName))) {
                LOG.info("reloading code " + sName);
                xxxRegistry.remove(sName);
            }
        }
        XXX xxx = xxxRegistry.get(sName);
        if (xxx == null) {
            Class clazz = COMPILER.compile(sCode, sName);
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                xxx = (XXX) XXX_FACTORY.newInstance(clazz);
            }
        }
        return xxx;

    }


    /**
     * From a file this will read the XXX source code, compile it, and add it to the list of current xxxs
     * a true response means that it was successful.
     *
     * @param file
     * @return true if the xxx in file successfully read, compiled, verified and added to Zuul
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public boolean putFilter(File file) throws Exception {
        String sName = file.getAbsolutePath() + file.getName();
        if (xxxClassLastModified.get(sName) != null && (file.lastModified() != xxxClassLastModified.get(sName))) {
            LOG.debug("reloading xxx " + sName);
            xxxRegistry.remove(sName);
        }
        XXX xxx = xxxRegistry.get(sName);
        if (xxx == null) {
            Class clazz = COMPILER.compile(file);
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                xxx = (XXX) XXX_FACTORY.newInstance(clazz);
                xxxRegistry.put(file.getAbsolutePath() + file.getName(), xxx);
                xxxClassLastModified.put(sName, file.lastModified());
                return true;
            }
        }

        return false;
    }
}
