package com.github.jjYBdx4IL.test.aop.annotationbased;

import org.springframework.stereotype.Component;

@Component
public class Performer2 {

    public static int testValue = 0;
    
    @Performer2Annotation
    public int getTestValue() {
        return testValue;
    }
}
