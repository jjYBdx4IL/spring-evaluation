package com.github.jjYBdx4IL.test.aop.classannotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// this creates an instance per application context!
@Aspect
// includes this aspect by simple classpath scanning:
@Component
public class Performer3Aspect {

    public Performer3Aspect() {
    }
    
    @Around(value = "execution(public * *(..)) && within(@Performer3Annotation *)")
    public Object aroundPerform3(ProceedingJoinPoint pjp) throws Throwable {
        Performer3.testValue += 2;
        Object o = pjp.proceed();
        Performer3.testValue += 3;
        return o;
    }
}
