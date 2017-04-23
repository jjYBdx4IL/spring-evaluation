package com.github.jjYBdx4IL.test.aop.classandmethodanno;

import org.springframework.stereotype.Component;

@Component
@Performer6Annotation
public class Performer6 {

    public static int testValue = 0;
    
    public int getTestValue() {
        return testValue;
    }
}
