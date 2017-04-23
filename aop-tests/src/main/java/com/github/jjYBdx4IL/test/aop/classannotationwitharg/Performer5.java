package com.github.jjYBdx4IL.test.aop.classannotationwitharg;

import org.springframework.stereotype.Component;

@Component
@Performer5Annotation(12)
public class Performer5 {

    public static int testValue = 0;
    
    public int getTestValue() {
        return testValue;
    }
}
