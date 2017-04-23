package com.github.jjYBdx4IL.test.aop.annotationwitharg;

import org.springframework.stereotype.Component;

@Component
public class Performer4 {

    public static int testValue = 0;
    
    @Performer4Annotation(11)
    public int getTestValue() {
        return testValue;
    }
}
