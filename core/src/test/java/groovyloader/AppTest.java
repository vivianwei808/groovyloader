package groovyloader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import groovy.lang.GroovyObject;
import groovyloader.groovy.GroovyCompiler;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public void say(){
        System.out.println("this is test compile file");
    }

    @Test
    public void testFileManager() throws Exception {
        XXXLoader.getInstance().setCompiler(new GroovyCompiler());
        GroovyFileManager.init(1,"/Users/wangwei/IdeaProjects/groovyloader/core/src/test/java/groovyloader/groovy");
        XXXRegistry xxxRegistry = XXXRegistry.instance();
        assertEquals(1,xxxRegistry.size());
        XXX entity = xxxRegistry.get("/Users/wangwei/IdeaProjects/groovyloader/core/src/test/java/groovyloader/groovy/YYY.javaYYY.java");
        entity.run();
    }


    @Test
    public void testCompilerStr() throws IllegalAccessException, InstantiationException {
        GroovyCompiler compiler = new GroovyCompiler();


        String code = "class test { public String hello(){return \"hello\" } } ";
        Class clazz = compiler.compile(code, "test");

        GroovyObject groovyObject = (GroovyObject) clazz.newInstance();
        Object[] args = {};
        String s = (String) groovyObject.invokeMethod("hello", args);
        System.out.println("测试：" + s);
    }



}
