package groovyloader;

public class DefaultXXXFactory implements  XXXFactory{
    @Override
    public XXX newInstance(Class clazz) throws InstantiationException, IllegalAccessException {
        return (XXX) clazz.newInstance();
    }
}
