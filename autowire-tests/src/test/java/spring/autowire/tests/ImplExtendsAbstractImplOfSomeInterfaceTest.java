package spring.autowire.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Github jjYBdx4IL Projects
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ImplExtendsAbstractImplOfSomeInterfaceTest {

    @Autowired
    private spring.autowire.tests.prototype.SomeInterface nonsingletonImpl1;
    @Autowired
    private spring.autowire.tests.prototype.SomeInterface nonsingletonImpl2;

    @Autowired
    private spring.autowire.tests.singleton.SomeInterface singletonImpl1;
    @Autowired
    private spring.autowire.tests.singleton.SomeInterface singletonImpl2;

    @Test
    public void testNonsingleton() {
        assertNotNull(nonsingletonImpl1);
        assertNotNull(nonsingletonImpl2);

        assertEquals("Hello", nonsingletonImpl1.getHelloString());
        assertEquals(spring.autowire.tests.prototype.PrototypeImplExtendsAbstractImplOfSomeInterface.class,
                nonsingletonImpl1.getClass());

        assertEquals("Hello", nonsingletonImpl2.getHelloString());
        assertEquals(spring.autowire.tests.prototype.PrototypeImplExtendsAbstractImplOfSomeInterface.class,
                nonsingletonImpl2.getClass());

        // verify that we actually use two different instances:
        nonsingletonImpl1.setValue("1");
        nonsingletonImpl2.setValue("2");
        assertEquals("1", nonsingletonImpl1.getValue());
    }

    @Test
    public void testSingleton() {
        assertNotNull(singletonImpl1);
        assertNotNull(singletonImpl2);

        assertEquals("Hello", singletonImpl1.getHelloString());
        assertEquals(spring.autowire.tests.singleton.SingletonImplExtendsAbstractImplOfSomeInterface.class,
                singletonImpl1.getClass());

        assertEquals("Hello", singletonImpl2.getHelloString());
        assertEquals(spring.autowire.tests.singleton.SingletonImplExtendsAbstractImplOfSomeInterface.class,
                singletonImpl2.getClass());

        // verify that we actually use two different instances:
        singletonImpl1.setValue("1");
        singletonImpl2.setValue("2");
        assertEquals("2", singletonImpl1.getValue());
    }
}
