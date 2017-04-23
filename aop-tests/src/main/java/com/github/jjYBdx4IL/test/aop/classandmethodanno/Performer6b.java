package com.github.jjYBdx4IL.test.aop.classandmethodanno;

import org.springframework.stereotype.Component;

@Component
public class Performer6b {

    public static int testValue = 0;
    
    @Performer6Annotation
    public int getTestValue() {
        return testValue;
    }
}
