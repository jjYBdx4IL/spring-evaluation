package com.github.jjYBdx4IL.test.aop.classannotation;

import org.springframework.stereotype.Component;

@Component
@Performer3Annotation
public class Performer3 {

    public static int testValue = 0;
    
    public int getTestValue() {
        return testValue;
    }
}
