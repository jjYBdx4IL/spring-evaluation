package com.github.jjYBdx4IL.test.aop;

import com.github.jjYBdx4IL.test.aop.annotationbased.Performer2;
import com.github.jjYBdx4IL.test.aop.annotationwitharg.Performer4;
import com.github.jjYBdx4IL.test.aop.basic.Performer;
import com.github.jjYBdx4IL.test.aop.basic.TestHelper;
import com.github.jjYBdx4IL.test.aop.classandmethodanno.Performer6;
import com.github.jjYBdx4IL.test.aop.classandmethodanno.Performer6b;
import com.github.jjYBdx4IL.test.aop.classandmethodanno.Performer6c;
import com.github.jjYBdx4IL.test.aop.classannotation.Performer3;
import com.github.jjYBdx4IL.test.aop.classannotationwitharg.Performer5;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class SpringAOPTest {

    @Autowired
    private Performer performer;
    @Autowired
    private Performer2 performer2;
    @Autowired
    private Performer3 performer3;
    @Autowired
    private Performer4 performer4;
    @Autowired
    private Performer5 performer5;
    @Autowired
    private Performer6 performer6;
    @Autowired
    private Performer6b performer6b;
    @Autowired
    private Performer6c performer6c;

    @Test
    public void testBasic() {
        performer.perform();

        System.out.println(TestHelper.msgs);

        assertEquals(6, TestHelper.msgs.size());
        
        // order not assured here:
        assertTrue(TestHelper.msgs.contains("The audience is taking their seats."));
        assertTrue(TestHelper.msgs.contains("The audience is turning off their cellphones"));
        assertEquals("Playing Twinkel..... : ", TestHelper.msgs.get(2));
        assertEquals("TOOT TOOT TOOT", TestHelper.msgs.get(3));
        try {
            assertEquals("Can do without pointcut ref!", TestHelper.msgs.get(4));
            assertEquals("CLAP CLAP CLAP CLAP CLAP", TestHelper.msgs.get(5));
        } catch (AssertionError ex) {
            assertEquals("Can do without pointcut ref!", TestHelper.msgs.get(5));
            assertEquals("CLAP CLAP CLAP CLAP CLAP", TestHelper.msgs.get(4));
        }
        
        assertFalse(TestHelper.msgs.contains("Inside aspect performance method."));
    }
    
    @Test
    public void testAnnotationbased() {
        assertEquals(0, Performer2.testValue);
        assertEquals(1, performer2.getTestValue());
        assertEquals(3, Performer2.testValue);
    }
    
    @Test
    public void testClassannotation() {
        assertEquals(0, Performer3.testValue);
        assertEquals(2, performer3.getTestValue());
        assertEquals(5, Performer3.testValue);
    }
    
    @Test
    public void testAnnotationwitharg() {
        assertEquals(0, Performer4.testValue);
        assertEquals(11, performer4.getTestValue());
        assertEquals(17, Performer4.testValue);
    }

    @Test
    public void testClassannotationwitharg() {
        assertEquals(0, Performer5.testValue);
        assertEquals(12, performer5.getTestValue());
        assertEquals(16, Performer5.testValue);
    }
    
    @Test
    public void testClassandmethodanno() {
        // class level annotation declaration
        assertEquals(0, Performer6.testValue);
        assertEquals(2, performer6.getTestValue());
        assertEquals(5, Performer6.testValue);
        
        // method level annotation declaration
        Performer6b.testValue = 0;
        assertEquals(0, Performer6b.testValue);
        assertEquals(2, performer6b.getTestValue());
        assertEquals(5, Performer6b.testValue);
        
        // annotation declared at class *and* method level
        Performer6c.testValue = 0;
        assertEquals(0, Performer6c.testValue);
        assertEquals(2, performer6c.getTestValue());
        assertEquals(5, Performer6c.testValue);
        // result: aspect gets called only once although the pointcut matches
        // at two positions.
    }
}
